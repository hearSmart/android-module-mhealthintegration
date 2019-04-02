package com.hearxgroup.integrationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hearxgroup.hearx.NiftyUtil;
import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract;
import com.hearxgroup.mhealthintegration.Models.Facility;
import com.hearxgroup.mhealthintegration.Models.HearscreenTest;
import com.hearxgroup.mhealthintegration.Models.HeartestTest;
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
import com.hearxgroup.mhealthintegration.Models.Patient;
import com.hearxgroup.mhealthintegration.Models.PeekAcuityTest;
import com.hearxgroup.mhealthintegration.TestRequestHelper;
import com.hearxgroup.mhealthintegration.Util;

import static com.hearxgroup.hearx.Constants.INDEX_HEARSCREEN;
import static com.hearxgroup.hearx.Constants.INDEX_HEARTEST;
import static com.hearxgroup.hearx.Constants.INDEX_PEEK;

/**
 * This activity showcases how an integration would proceed using intents and the ContentRetrieverInterface to retrieve data directly from the mHealth app
 */
public class IntegrationViaIntentActivity extends AppCompatActivity implements MHealthTestRetrieverContract.ContentRetrieverInterface {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integration_via_intent);
        //ONCLICK LISTENER FOR REQUESTING A TEST
        findViewById(R.id.btn_test).setOnClickListener(v -> {
            requestMHTest(buildTestPatient()); //REQUEST TEST WITH PATIENT
            //requestMHTest(null); //REQUEST TEST WITH NO PATIENT
        });
        //HANDLE NEW INTENT
        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleLaunchIntent(intent);
        setIntent(null);
    }

    /**
     * Method checks the received intent to see if result came from mHealthStudio app
     * @param intent
     */
    private void handleLaunchIntent(Intent intent) {
        Log.d(TAG, "handleLaunchIntent");
        if(TestRequestHelper.getGeneratedTestIdFromIntent(intent)!=null) { //RETURN FROM A TEST REQUEST OCCURRED
            String mHealthGeneratedTestId = TestRequestHelper.getGeneratedTestIdFromIntent(intent);
            Log.d(TAG, "RETURN FROM AN MHEALTH TEST REQUEST OCCURRED");
            Log.d(TAG, "mHealthGeneratedTestId: "+mHealthGeneratedTestId);
            Log.d(TAG, "testType: "+TestRequestHelper.getTestTypeFromIntent(intent));

            //RETRIEVE TEST RESULT FROM MHEALTH APP VIA A LOADER MANAGER
            TestRequestHelper.retrieveTestResult(
                    IntegrationViaIntentActivity.this,
                    getLoaderManager(),
                    IntegrationViaIntentActivity.this,
                    TestRequestHelper.getTestTypeFromIntent(intent),
                    mHealthGeneratedTestId);
        }
    }

    private void requestMHTest(@Nullable Patient patient) {
        //GENERATE UNIQUE 24 CHAR TEST ID
        String testId = NiftyUtil.getRandomSequence();
        //BUILD TEST REQUEST
        MHealthTestRequest testRequest =
                MHealthTestRequest.build(
                        testId, //UNIQUE TEST ID
                        "com.hearxgroup.mhealthintegrationdemo.mhealthtest", //REPLACE WITH ACTION NAME AS DEFINED IN YOUR MANIFEST
                        patient, //PATIENT OBJECT OR NULL
                        INDEX_HEARTEST); //REQUIRED TEST(INDEX_HEARSCREEN, INDEX_HEARTEST, INDEX_PEEK, INDEX_SEALCHECK, INDEX_HEARSCOPE, CODE_UNSET)
        //UTILITY TO HELP YOU VALIDATE YOUR TEST REQUEST
        String requestValidationResponse = Util.validateTestRequest(IntegrationViaIntentActivity.this, testRequest);
        if(requestValidationResponse==null)
            //VALIDATION WAS PASSED, INITIATE TEST REQUEST
            TestRequestHelper.startTest(IntegrationViaIntentActivity.this, testRequest);
        else
            //VALIDATION ERROR OCCURRED
            Log.e(TAG, "Validation error:"+requestValidationResponse);
    }

    @Override
    public void onRetrieveTestHearScreen(HearscreenTest hearscreenTest) {
        Log.d(TAG, "onRetrieveTestHearScreen");
        Log.d(TAG, "hearscreenTest:"+hearscreenTest.toJson());
        Log.d(TAG, "generatedPatientId: "+hearscreenTest.getGeneratedPatientId());
        if(hearscreenTest.getGeneratedPatientId()!=null)
            TestRequestHelper.retrievePatient(
                    IntegrationViaIntentActivity.this,
                    getLoaderManager(),
                    IntegrationViaIntentActivity.this,
                    hearscreenTest.getGeneratedPatientId());
    }

    @Override
    public void onRetrieveTestHearTest(HeartestTest heartestTest) {
        Log.d(TAG, "onRetrieveTestHearTest");
        Log.d(TAG, "heartestTest:"+heartestTest.toJson());
        Log.d(TAG, "generatedPatientId: "+heartestTest.getGeneratedPatientId());
        if(heartestTest.getGeneratedPatientId()!=null) {
            TestRequestHelper.retrievePatient(
                    IntegrationViaIntentActivity.this,
                    getLoaderManager(),
                    IntegrationViaIntentActivity.this,
                    heartestTest.getGeneratedPatientId());
        }
    }

    @Override
    public void onRetrieveTestPeekAcuity(PeekAcuityTest peekAcuityTest) {
        Log.d(TAG, "onRetrieveTestPeekAcuity");
        Log.d(TAG, "peekAcuityTest:"+peekAcuityTest.toJson());
        Log.d(TAG, "generatedPatientId: "+peekAcuityTest.getGeneratedPatientId());
        if(peekAcuityTest.getGeneratedPatientId()!=null)
            TestRequestHelper.retrievePatient(
                    IntegrationViaIntentActivity.this,
                    getLoaderManager(),
                    IntegrationViaIntentActivity.this,
                    peekAcuityTest.getGeneratedPatientId());
    }

    @Override
    public void onRetrievePatient(Patient patient) {
        Log.d(TAG, "onRetrievePatient");
        Log.d(TAG, "patient:"+patient.toJson());
    }

    @Override
    public void onRetrieveFacility(Facility facility) {
        Log.d(TAG, "onRetrieveFacility");
        Log.d(TAG, "facility:"+facility.toJson());
    }

    @Override
    public void onRetrieveContentError(String errorMessage) {
        Log.e(TAG, "onRetrieveContentError: "+errorMessage);
    }

    private Patient buildTestPatient() {
        return Patient.build(
                "Example",//firstName
                "Patient",//lastName
                "1989-09-15",//YYYY-MM-dd
                "male", //male/female
                "eng",//iso3 languageCode
                null,//email
                null,//contactNumber
                null,//identificationNumber (Users national identification number)
                null);//referenceNumber (Any reference string you have to connect with your system)
    }
}

package com.hearxgroup.integrationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract;
import com.hearxgroup.mhealthintegration.Models.Facility;
import com.hearxgroup.mhealthintegration.Models.HearscreenTest;
import com.hearxgroup.mhealthintegration.Models.HeartestTest;
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
import com.hearxgroup.mhealthintegration.Models.Patient;
import com.hearxgroup.mhealthintegration.Models.PeekAcuityTest;
import com.hearxgroup.mhealthintegration.TestRequestHelper;
import com.hearxgroup.mhealthintegration.Util;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements MHealthTestRetrieverContract.ContentRetrieverInterface {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test).setOnClickListener(v -> {
            requestMHTest(buildTestPatient()); //REQUEST TEST WITH PATIENT
            //requestMHTest(null); //REQUEST TEST WITH NO PATIENT
        });
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
            TestRequestHelper.retrieveTestResult(
                    MainActivity.this,
                    getLoaderManager(),
                    MainActivity.this,
                    TestRequestHelper.getTestTypeFromIntent(intent),
                    mHealthGeneratedTestId);
        }
    }

    private String getRandomSequence() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private Patient buildTestPatient() {
        return Patient.build(
                "John",//firstName
                "Smith",//lastName
                "1989-09-15",//YYYY-MM-dd
                "male", //male/female
                "eng",//iso3 languageCode
                null,//email
                null,//contactNumber
                null,//identificationNumber (Users national identification number)
                null);//referenceNumber (Any reference string you have to connect with your system)
    }

    private void requestMHTest(@Nullable Patient patient) {
        //GENERATE UNIQUE 24 CHAR TEST ID
        String testId = getRandomSequence();
        //BUILD TEST REQUEST
        MHealthTestRequest testRequest =
                MHealthTestRequest.build(
                        testId, //UNIQUE TEST ID
                        "com.hearxgroup.mhealthintegrationdemo.mhealthtest", //REPLACE WITH ACTION NAME AS DEFINED IN YOUR MANIFEST
                        patient); //PATIENT OBJECT OR NULL
        //UTILITY TO HELP YOU VALIDATE YOUR TEST REQUEST
        String requestValidationResponse = Util.validateTestRequest(MainActivity.this, testRequest);
        if(requestValidationResponse==null)
            //VALIDATION WAS PASSED, INITIATE TEST REQUEST
            TestRequestHelper.startTest(MainActivity.this, testRequest);
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
                    MainActivity.this,
                    getLoaderManager(),
                    MainActivity.this,
                    hearscreenTest.getGeneratedPatientId());
    }

    @Override
    public void onRetrieveTestHearTest(HeartestTest heartestTest) {
        Log.d(TAG, "onRetrieveTestHearTest");
        Log.d(TAG, "heartestTest:"+heartestTest.toJson());
        Log.d(TAG, "generatedPatientId: "+heartestTest.getGeneratedPatientId());
        if(heartestTest.getGeneratedPatientId()!=null) {
            TestRequestHelper.retrievePatient(
                    MainActivity.this,
                    getLoaderManager(),
                    MainActivity.this,
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
                    MainActivity.this,
                    getLoaderManager(),
                    MainActivity.this,
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
}

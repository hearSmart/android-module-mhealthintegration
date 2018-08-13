package com.hearxgroup.integrationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

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
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GENERATE UNIQUE 24 CHAR TEST ID
                String testId = getRandomSequence();
                //GENERATE PATIENT (NOT REQUIRED)
                Patient demoPatient = Patient.build(
                        "Will",//firstName
                        "Turner",//lastName
                        "1987-05-07",//YYYY-MM-dd
                        "male", //male/female
                        "eng",//iso3 languageCode
                        null,//email
                        null,//contactnumber
                        null,//identificationNumber (Users national identification number)
                        null);//referenceNumber (Any reference string you have to connect with your system)
                //BUILD TEST REQUEST
                MHealthTestRequest testRequest =
                        MHealthTestRequest.build(
                                testId,
                                "com.hearxgroup.mhealthintegrationdemo.mhealthtest", //The action name defined in your manifest
                                demoPatient);
                //VALIDATE TEST REQUEST
                String requestValidationResponse = Util.validateTestRequest(MainActivity.this, testRequest);
                if(requestValidationResponse==null)
                    //VALIDATION WAS PASSED, INITIATE MHEALTH
                    TestRequestHelper.startTest(MainActivity.this, testRequest);
                else
                    //VALIDATION ERROR OCCURRED
                    Log.e("MainActivity", "Validation error:"+requestValidationResponse);
            }
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
        String mHealthGeneratedTestId = TestRequestHelper.getGeneratedTestIdFromIntent(intent);
        if(mHealthGeneratedTestId!=null) {
            //RETURN FROM A TEST REQUEST OCCURRED
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

    public static String getRandomSequence() {
        return UUID.randomUUID().toString().replaceAll("-", "");
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
        if(heartestTest.getGeneratedPatientId()!=null)
            TestRequestHelper.retrievePatient(
                    MainActivity.this,
                    getLoaderManager(),
                    MainActivity.this,
                    heartestTest.getGeneratedPatientId());
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

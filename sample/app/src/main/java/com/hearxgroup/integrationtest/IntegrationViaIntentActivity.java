/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 */

package com.hearxgroup.integrationtest;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract;
import com.hearxgroup.mhealthintegration.Models.Facility;
import com.hearxgroup.mhealthintegration.Models.HearriskTest;
import com.hearxgroup.mhealthintegration.Models.HearspeechTest;
import com.hearxgroup.mhealthintegration.Models.VulaVisionTest;
import com.hearxgroup.mhealthintegration.NiftyUtil;
import com.hearxgroup.mhealthintegration.Models.HearscreenTest;
import com.hearxgroup.mhealthintegration.Models.HeartestTest;
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
import com.hearxgroup.mhealthintegration.Models.Patient;
import com.hearxgroup.mhealthintegration.TestRequestHelper;
import com.hearxgroup.mhealthintegration.Util;
import timber.log.Timber;
import com.hearxgroup.mhealth.resources.Const;

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
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
        Timber.d( "handleLaunchIntent");
        if(TestRequestHelper.INSTANCE.getGeneratedTestIdFromIntent(intent)!=null) { //RETURN FROM A TEST REQUEST OCCURRED
            String mHealthGeneratedTestId = TestRequestHelper.INSTANCE.getGeneratedTestIdFromIntent(intent);
            Timber.d( "RETURN FROM AN MHEALTH TEST REQUEST OCCURRED");
            Timber.d( "mHealthGeneratedTestId: "+mHealthGeneratedTestId);
            Timber.d( "testType: "+TestRequestHelper.INSTANCE.getTestTypeFromIntent(intent));

            //RETRIEVE TEST RESULT FROM MHEALTH APP VIA A LOADER MANAGER
            TestRequestHelper.INSTANCE.retrieveTestResult(
                    IntegrationViaIntentActivity.this,
                    getLoaderManager(),
                    IntegrationViaIntentActivity.this,
                    TestRequestHelper.INSTANCE.getTestTypeFromIntent(intent),
                    mHealthGeneratedTestId);
        }
    }

    private void requestMHTest(@Nullable Patient patient) {
        //GENERATE UNIQUE 24 CHAR TEST ID
        String testId = NiftyUtil.INSTANCE.getRandomSequence();
        //BUILD TEST REQUEST
        Log.d(TAG, "TEST INDEX  " + String.valueOf(Const.INSTANCE.getAppIndexFromTest(Const.TEST.HEARSPEECH).getValue()));
        MHealthTestRequest testRequest =
                MHealthTestRequest.build(
                        testId, //UNIQUE TEST ID
                        "com.hearxgroup.mhealthintegrationdemo.mhealthtest", //REPLACE WITH ACTION NAME AS DEFINED IN YOUR MANIFEST
                        patient, //PATIENT OBJECT OR NULL
                        //REPLACE TEST_TYPE WITH THE TEST
                        //REQUIRED TEST( Const.INSTANCE.getAppIndexFromTest(Const.TEST.TEST_TYPE))
                        Const.INSTANCE.getAppIndexFromTest(Const.TEST.HEARSPEECH).getValue());
        //UTILITY TO HELP YOU VALIDATE YOUR TEST REQUEST
        String requestValidationResponse = Util.validateTestRequest(IntegrationViaIntentActivity.this, testRequest);
        if(requestValidationResponse==null)
            //VALIDATION WAS PASSED, INITIATE TEST REQUEST
            TestRequestHelper.INSTANCE.startTest(IntegrationViaIntentActivity.this, testRequest);
        else
            //VALIDATION ERROR OCCURRED
            Log.e(TAG, "Validation error:"+requestValidationResponse);
    }

    @Override
    public void onRetrieveTestHearScreen(HearscreenTest hearscreenTest) {
        Timber.d("onRetrieveTestHearScreen");
        Timber.d("hearscreenTest:" + hearscreenTest.toJson());
        Timber.d("generatedPatientId: " + hearscreenTest.getPatientUuid());
        if(hearscreenTest.getPatientUuid()!=null)
            TestRequestHelper.INSTANCE.retrievePatient(
                    IntegrationViaIntentActivity.this,
                    getLoaderManager(),
                    IntegrationViaIntentActivity.this,
                    hearscreenTest.getPatientUuid());
    }

   @Override
    public void onRetrieveTestHearTest(HeartestTest heartestTest) {
        Timber.d( "onRetrieveTestHearTest");
        Timber.d( "heartestTest:"+heartestTest.toJson());
        Timber.d( "generatedPatientId: "+heartestTest.getPatientUuid());
        if(heartestTest.getPatientUuid()!=null) {
            TestRequestHelper.INSTANCE.retrievePatient(
                    IntegrationViaIntentActivity.this,
                    getLoaderManager(),
                    IntegrationViaIntentActivity.this,
                    heartestTest.getPatientUuid());
        }
    }

    @Override
    public void onRetrieveTestPeekAcuity(VulaVisionTest vulaVisionTest) {
        Timber.d( "onRetrieveTestPeekAcuity");
        Timber.d( "vulaVisionTest:"+vulaVisionTest.Companion.toJson());
        Timber.d( "generatedPatientId: "+vulaVisionTest.getPatientUuid());
        if(vulaVisionTest.getPatientUuid()!=null)
            TestRequestHelper.INSTANCE.retrievePatient(
                    IntegrationViaIntentActivity.this,
                    getLoaderManager(),
                    IntegrationViaIntentActivity.this,
                    vulaVisionTest.getPatientUuid());
    }

    @Override
    public void onRetrieveTestHearSpeech(HearspeechTest hearspeechTest) {
        Timber.d( "onRetrieveTestPeekAcuity");
        Timber.d( "hearspeechTest:"+hearspeechTest.Companion.toJson());
        Timber.d( "generatedPatientId: "+hearspeechTest.getPatientUuid());
        if(hearspeechTest.getPatientUuid()!=null)
            TestRequestHelper.INSTANCE.retrievePatient(
                    IntegrationViaIntentActivity.this,
                    getLoaderManager(),
                    IntegrationViaIntentActivity.this,
                    hearspeechTest.getPatientUuid());
    }

    @Override
    public void onRetrieveTestHearRisk(HearriskTest hearriskTest) {

        
    }

    @Override
    public void onRetrievePatient(Patient patient) {
        Timber.d( "onRetrievePatient");
        Timber.d( "patient:"+patient.toJson());
    }

    @Override
    public void onRetrieveFacility(Facility facility) {
        Timber.d( "onRetrieveFacility");
        Timber.d( "facility:"+facility.toJson());
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

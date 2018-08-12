package com.hearxgroup.integrationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract;
import com.hearxgroup.mhealthintegration.Models.HearscreenTest;
import com.hearxgroup.mhealthintegration.Models.HeartestTest;
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
import com.hearxgroup.mhealthintegration.Models.Patient;
import com.hearxgroup.mhealthintegration.Models.PeekAcuityTest;
import com.hearxgroup.mhealthintegration.TestRequestHelper;
import com.hearxgroup.mhealthintegration.Util;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements MHealthTestRetrieverContract.TestRetrieverInterface {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String testId = getRandomSequence();
                Patient demoPatient = Patient.build(
                        "James",//firstName
                        "Langley",//lastName
                        "1993-02-21",//YYYY-MM-dd
                        "male", //male/female
                        "eng",//iso3 languageCode
                        null,//email
                        null,//contactnumber
                        null,//identificationNumber (Users national identification number)
                        null);//referenceNumber (Any reference string you have to connect with your system)
                MHealthTestRequest testRequest =
                        MHealthTestRequest.build(
                                testId,
                                "com.hearxgroup.mhealthintegrationdemo.mhealthtest",
                                demoPatient);

                String requestValidationResponse = Util.validateTestRequest(MainActivity.this, testRequest);
                if(requestValidationResponse==null)
                    TestRequestHelper.startTest(MainActivity.this, testRequest);
                else
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

    private void handleLaunchIntent(Intent intent) {
        String mHealthTestId = TestRequestHelper.getTestIdFromIntent(intent);
        if(mHealthTestId!=null) {
            //RETURN FROM A TEST REQUEST OCCURRED
            Log.d(TAG, "RETURN FROM AN MHEALTH TEST REQUEST OCCURRED");
            Log.d(TAG, "mHealthTestId: "+mHealthTestId);
            Log.d(TAG, "testType: "+TestRequestHelper.getTestTypeFromIntent(intent));
            TestRequestHelper.retrieveTestResult(
                    MainActivity.this,
                    getLoaderManager(),
                    MainActivity.this,
                    TestRequestHelper.getTestTypeFromIntent(intent),
                    mHealthTestId);
        }
    }

    public static String getRandomSequence() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public void onRetrieveTestHearScreen(HearscreenTest hearscreenTest) {
        Log.d(TAG, "onRetrieveTestHearScreen");
        Log.d(TAG, "hearscreenTest:"+hearscreenTest.toJson());
    }

    @Override
    public void onRetrieveTestHearTest(HeartestTest heartestTest) {
        Log.d(TAG, "onRetrieveTestHearTest");
        Log.d(TAG, "heartestTest:"+heartestTest.toJson());
    }

    @Override
    public void onRetrieveTestPeekAcuity(PeekAcuityTest peekAcuityTest) {
        Log.d(TAG, "onRetrieveTestPeekAcuity");
        Log.d(TAG, "peekAcuityTest:"+peekAcuityTest.toJson());
    }

    @Override
    public void onRetrieveContentError(String errorMessage) {
        Log.e(TAG, "onRetrieveContentError: "+errorMessage);
    }
}

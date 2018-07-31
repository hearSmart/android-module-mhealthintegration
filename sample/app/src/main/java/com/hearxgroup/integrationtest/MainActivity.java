package com.hearxgroup.integrationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hearxgroup.mhealthintegration.Constants;
import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract;
import com.hearxgroup.mhealthintegration.Models.HearscreenTest;
import com.hearxgroup.mhealthintegration.Models.HeartestTest;
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
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
                MHealthTestRequest testRequest =
                        MHealthTestRequest.build(
                                testId,
                                "male",
                                "1992-02-21",
                                "John",
                                "Smith",
                                "eng",
                                null,
                                null,
                                null,
                                null,
                                "com.hearxgroup.mhealthintegrationdemo.mhealthtest");

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
            TestRequestHelper.retrieveTestResult(
                    MainActivity.this,
                    getLoaderManager(),
                    MainActivity.this,
                    Constants.INDEX_HEARSCREEN,
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
    public void onRetrieveTestError(String errorMessage) {
        Log.e(TAG, "onRetrieveTestError: "+errorMessage);
    }
}

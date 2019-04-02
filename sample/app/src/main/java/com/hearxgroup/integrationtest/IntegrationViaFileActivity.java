package com.hearxgroup.integrationtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.hearxgroup.hearx.Constants;
import com.hearxgroup.hearx.FileUtil;
import com.hearxgroup.hearx.MiscUtils;
import com.hearxgroup.hearx.NiftyUtil;
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
import com.hearxgroup.mhealthintegration.Models.Patient;
import com.hearxgroup.mhealthintegration.TestRequestHelper;
import com.hearxgroup.mhealthintegration.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import io.reactivex.Single;

import static com.hearxgroup.hearx.Constants.INDEX_HEARSCREEN;
import static com.hearxgroup.hearx.Constants.INDEX_HEARTEST;
import static com.hearxgroup.hearx.Constants.INDEX_PEEK;

/**
 * This activity showcases how an integration would proceed using files to request and retrieve data from the mHealth app
 */
public class IntegrationViaFileActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integration_via_file);
        //REQUESTING RELEVANT PERMISSIONS
        if (ContextCompat.checkSelfPermission(IntegrationViaFileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(IntegrationViaFileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(IntegrationViaFileActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
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
     * Method attempts to read extrenal file for data from mHealthApp.
     *
     * @param intent
     */
    private void handleLaunchIntent(Intent intent) {
        Log.d(TAG, "handleLaunchIntent");
        //0. RETRIEVE TEST TYPE FROM RECEIVED INTENT (OPTIONAL)
        //1. READ FILE CONTENTS
        //2. DELETE FILE

        //RETRIEVE THE COMPLETED TEST PATH CONSTANT FROM THE HEARX LIB
        String readPath = new Constants().getTestCompletePath();
        File file = new File(readPath);
        if(file.exists()) {
            Log.d(TAG, "file exists!");
            //YOU CAN RETRIEVE THE TEST TYPE LIKE THIS IF NECESSARY
            int testType = TestRequestHelper.getTestTypeFromIntent(intent);
            Log.d(TAG, "testType: "+testType);
            readFile(readPath)
                    .subscribe(jsonFileContents -> {
                        Log.d(TAG, "jsonFileContents:" + jsonFileContents);
                        //RETRIEVE TEST FROM FILE AND DO WHAT YOU WILL WITH TEST OBJECT
                        switch(testType) {
                            case INDEX_HEARSCREEN: Util.buildHearScreenTestFromJson(jsonFileContents); break;
                            case INDEX_HEARTEST: Util.buildHearTestTestFromJson(jsonFileContents);  break;
                            case INDEX_PEEK: Util.buildPeekAcuityTestFromJson(jsonFileContents);  break;
                        }
                        removeFile(readPath)
                                .subscribe(deleteStatus -> Log.d(TAG, "deleteStatus:" + deleteStatus));
                    });
        }
        else
            Log.d(TAG, "file does not exist");
    }

    private void requestMHTest(@Nullable Patient patient) {
        //GENERATE UNIQUE 24 CHAR TEST ID
        String testId = NiftyUtil.getRandomSequence();
        //BUILD TEST REQUEST
        MHealthTestRequest testRequest =
                MHealthTestRequest.build(
                        testId, //UNIQUE TEST ID
                        "com.hearxgroup.mhealthintegrationdemo.mhealthtestviafile",// use action name as defined in your manifest OR use "close" if you just want mHealth app to close after a test
                        patient, //PATIENT OBJECT OR NULL
                        INDEX_HEARTEST); //REQUIRED TEST(INDEX_HEARSCREEN, INDEX_HEARTEST, INDEX_PEEK, INDEX_SEALCHECK, INDEX_HEARSCOPE, CODE_UNSET)

        //RETRIEVE THE REQUESTED TEST PATH CONSTANT FROM THE HEARX LIB
        String filePath = new Constants().getTestRequestPath();
        FileUtil.writeFile(testRequest.toJson(), filePath)
                .subscribe(writeResult -> {
                    //UTILITY TO HELP YOU VALIDATE YOUR TEST REQUEST
                    String requestValidationResponse = Util.validateTestRequest(IntegrationViaFileActivity.this, testRequest);
                    if(requestValidationResponse==null)
                        //VALIDATION WAS PASSED, INITIATE TEST REQUEST
                        startActivity(MiscUtils.getLaunchAppIntent(getPackageManager(), null, "com.hearxgroup.mhealthstudio.intent.action.testrequestfromfile"));
                    else
                        //VALIDATION ERROR OCCURRED
                        Log.e(TAG, "Validation error:"+requestValidationResponse);
                });
    }

    private Patient buildTestPatient() {
        return Patient.build(
                "Example",//firstName
                "Patient",//lastName
                "1989-09-15",//YYYY-MM-dd
                "male", //male/female
                "eng",//iso3 languageCode of patient first language
                null,//email
                null,//contactNumber
                null,//identificationNumber (Users national identification number)
                null);//referenceNumber (Any reference string you have to connect with your system)
    }

    private static Single<String> readFile(String path) {
        return Single.create(emitter -> {
            String fileString = "";
            File readFile = new File(path);
            FileInputStream fileInputStream = new FileInputStream(readFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String aDataRow = "";
            while ((aDataRow = myReader.readLine()) != null) {
                fileString += aDataRow;
            }
            myReader.close();
            fileInputStream.close();
            emitter.onSuccess(fileString);
        });
    }

    private static Single<Boolean> removeFile(String path) {
        return Single.create(emitter -> {
            File file = new File(path);
            emitter.onSuccess(file.delete());
        });
    }
}

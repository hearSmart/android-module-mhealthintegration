package com.hearxgroup.integrationtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hearxgroup.encryption.Logger;
import com.hearxgroup.hearx.FileUtil;
import com.hearxgroup.hearx.MiscUtils;
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
import com.hearxgroup.mhealthintegration.Models.Patient;
import com.hearxgroup.mhealthintegration.TestRequestHelper;
import com.hearxgroup.mhealthintegration.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import io.reactivex.Single;

import static com.hearxgroup.hearx.Constants.INDEX_HEARTEST;

/**
 * This activity showcases how an integration would proceed using files to request and retrieve data from the mHealth app
 */
public class IntegrationViaFileActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integration_via_file);

        if (ContextCompat.checkSelfPermission(IntegrationViaFileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(IntegrationViaFileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(IntegrationViaFileActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }

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
        //0. RETRIEVE TEST TYPE FROM RECEIVED INTENT (OPTIONAL)
        //1. READ FILE CONTENTS
        //2. DELETE FILE
        String readPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/mhealth/mhealthtest_complete.txt";
        File file = new File(readPath);
        if(file.exists()) {
            Log.d(TAG, "file exists!");
            //IF TEST TYPE IS REQUIRED, OBTAIN AS FOLLOWS:
            int testType = TestRequestHelper.getTestTypeFromIntent(intent);
            Log.d(TAG, "testType: "+testType);

            readFile(readPath)
                    .subscribe(fileContents -> {
                        Logger.d(TAG, "fileContents:" + fileContents);
                        //HANDLE FILE CONTENTS
                        removeFile(readPath)
                                .subscribe(deleteStatus -> Logger.d(TAG, "deleteStatus:" + deleteStatus));
                    });
        }
        else
            Log.d(TAG, "file does not exist");
    }

    private void requestMHTest(@Nullable Patient patient) {
        //GENERATE UNIQUE 24 CHAR TEST ID
        String testId = getRandomSequence();
        //BUILD TEST REQUEST
        MHealthTestRequest testRequest =
                MHealthTestRequest.build(
                        testId, //UNIQUE TEST ID
                        "com.hearxgroup.mhealthintegrationdemo.mhealthtestviafile", //REPLACE WITH ACTION NAME AS DEFINED IN YOUR MANIFEST
                        patient, //PATIENT OBJECT OR NULL
                        INDEX_HEARTEST); //REQUIRED TEST(INDEX_HEARSCREEN, INDEX_HEARTEST, INDEX_PEEK, INDEX_SEALCHECK, INDEX_HEARSCOPE, CODE_UNSET)

        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/mhealth/mhealthtest.txt";
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

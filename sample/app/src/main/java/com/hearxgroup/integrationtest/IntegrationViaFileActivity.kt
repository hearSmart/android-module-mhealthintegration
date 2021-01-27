/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 */
package com.hearxgroup.integrationtest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hearxgroup.mhealth.resources.Const
import com.hearxgroup.mhealth.resources.Const.TEST
import com.hearxgroup.mhealth.resources.Const.getAppIndexFromTest
import com.hearxgroup.mhealthintegration.AppLinkUtil.getLaunchAppIntent
import com.hearxgroup.mhealthintegration.Const.getTestRequestPath
import com.hearxgroup.mhealthintegration.FileUtil.Companion.writeFile
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest
import com.hearxgroup.mhealthintegration.Models.Patient
import com.hearxgroup.mhealthintegration.NiftyUtil.randomSequence
import com.hearxgroup.mhealthintegration.TestRequestHelper.getTestTypeFromIntent
import com.hearxgroup.mhealthintegration.Util
import io.reactivex.Single
import io.reactivex.SingleEmitter
import timber.log.Timber
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
/**
 * This activity showcases how an integration would proceed using files to request and retrieve data from the mHealth app
 */
class IntegrationViaFileActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_integration_via_file)
        //REQUESTING RELEVANT PERMISSIONS
        if (ContextCompat.checkSelfPermission(this@IntegrationViaFileActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this@IntegrationViaFileActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@IntegrationViaFileActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE),
                    1)
        }
        //ONCLICK LISTENER FOR REQUESTING A TEST
        findViewById<View>(R.id.btn_test).setOnClickListener { v: View? ->
            requestMHTest(buildTestPatient()) //REQUEST TEST WITH PATIENT
        }
        //HANDLE NEW INTENT
        onNewIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleLaunchIntent(intent)
        setIntent(null)
    }

    /**
     * Method attempts to read extrenal file for data from mHealthApp.
     *
     * @param intent
     */
    private fun handleLaunchIntent(intent: Intent) {
        Timber.d("handleLaunchIntent")
        //0. RETRIEVE TEST TYPE FROM RECEIVED INTENT (OPTIONAL)
        //1. READ FILE CONTENTS
        //2. DELETE FILE

        //RETRIEVE THE COMPLETED TEST PATH CONSTANT FROM THE HEARX LIB
        val readPath: String = getTestRequestPath()!!
        val file = File(readPath)
        if (file.exists()) {
            Timber.d("file exists!")
            //YOU CAN RETRIEVE THE TEST TYPE LIKE THIS IF NECESSARY
            val testType = getTestTypeFromIntent(intent)
            Timber.d("testType: $testType")
            readFile(readPath)
                    .subscribe { jsonFileContents: String ->
                        Timber.d("jsonFileContents:$jsonFileContents")
                        when (testType) {
                            Const.getAppIndexFromTest(Const.TEST.HEARSCREEN).value -> Util.buildHearScreenTestFromJson(jsonFileContents)
                            Const.getAppIndexFromTest(Const.TEST.HEARTEST).value -> Util.buildHearTestTestFromJson(jsonFileContents)
                            Const.getAppIndexFromTest(Const.TEST.HEARSPEECH).value -> Util.buildHearSpeechTestFromJson(jsonFileContents)
                            Const.getAppIndexFromTest(Const.TEST.VISION).value -> Util.buildVulaVisionTestFromJson(jsonFileContents)
                            Const.getAppIndexFromTest(Const.TEST.HEARRISK).value -> Util.buildHearRiskTestFromJson(jsonFileContents)
                        }
                        removeFile(readPath)
                                .subscribe { deleteStatus: Boolean -> Timber.d("deleteStatus:$deleteStatus") }
                    }
        } else Timber.d("file does not exist")
    }

    private fun requestMHTest(patient: Patient?) {
        //GENERATE UNIQUE 24 CHAR TEST ID
        val testId = randomSequence
        //BUILD TEST REQUEST
        val testRequest = MHealthTestRequest.build(
                testId,  //UNIQUE TEST ID
                "com.hearxgroup.mhealthintegrationdemo.mhealthtestviafile",  // use action name as defined in your manifest OR use "close" if you just want mHealth app to close after a test
                patient,  //PATIENT OBJECT OR NULL
                //PATIENT OBJECT OR NULL
                //REPLACE TEST_TYPE WITH THE TEST
                //REQUIRED TEST( Const.INSTANCE.getAppIndexFromTest(Const.TEST.TEST_TYPE))
                getAppIndexFromTest(TEST.HEARTEST).value)

        //RETRIEVE THE REQUESTED TEST PATH CONSTANT FROM THE HEARX LIB
        val filePath: String = getTestRequestPath()!!
        writeFile(testRequest.toJson(), filePath)
                .subscribe { writeResult ->
                    //UTILITY TO HELP YOU VALIDATE YOUR TEST REQUEST
                    val requestValidationResponse = Util.validateTestRequest(this@IntegrationViaFileActivity, testRequest)
                    if (requestValidationResponse == null) //VALIDATION WAS PASSED, INITIATE TEST REQUEST
                        startActivity(getLaunchAppIntent(packageManager, null, "com.hearxgroup.mhealthstudio.intent.action.testrequestfromfile")) else  //VALIDATION ERROR OCCURRED
                        Log.e(TAG, "Validation error:$requestValidationResponse")
                }
    }

    private fun buildTestPatient(): Patient {
        return Patient.build(
                "Example",  //firstName
                "Patient",  //lastName
                "1989-09-15",  //YYYY-MM-dd
                "male",  //male/female
                "eng",  //iso3 languageCode of patient first language
                null,  //email
                null,  //contactNumber
                null,  //identificationNumber (Users national identification number)
                null) //referenceNumber (Any reference string you have to connect with your system)
    }

    companion object {
        private fun readFile(path: String): Single<String> {
            return Single.create { emitter: SingleEmitter<String> ->
                var fileString = ""
                val readFile = File(path)
                val fileInputStream = FileInputStream(readFile)
                val myReader = BufferedReader(InputStreamReader(fileInputStream))
                var aDataRow = ""
                while (myReader.readLine().also { aDataRow = it } != null) {
                    fileString += aDataRow
                }
                myReader.close()
                fileInputStream.close()
                emitter.onSuccess(fileString)
            }
        }

        private fun removeFile(path: String): Single<Boolean> {
            return Single.create { emitter: SingleEmitter<Boolean> ->
                val file = File(path)
                emitter.onSuccess(file.delete())
            }
        }
    }
}
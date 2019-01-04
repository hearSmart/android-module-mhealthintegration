package com.hearxgroup.mhealthintegration;

import android.content.Context;

import com.google.gson.Gson;
import com.hearxgroup.mhealthintegration.Models.HearscreenFrequencyResult;
import com.hearxgroup.mhealthintegration.Models.HearscreenTest;
import com.hearxgroup.mhealthintegration.Models.HeartestFrequencyResult;
import com.hearxgroup.mhealthintegration.Models.HeartestTest;
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
import com.hearxgroup.mhealthintegration.Models.PeekAcuityTest;

import static com.hearxgroup.hearx.Constants.CODE_UNSET;
import static com.hearxgroup.hearx.Constants.INDEX_HEARSCOPE;
import static com.hearxgroup.hearx.Constants.INDEX_HEARSCREEN;
import static com.hearxgroup.hearx.Constants.INDEX_HEARTEST;
import static com.hearxgroup.hearx.Constants.INDEX_PEEK;
import static com.hearxgroup.hearx.Constants.INDEX_SEALCHECK;

/**
 * Copyright (c) 2017 hearX Group (Pty) Ltd. All rights reserved
 * Created by David Howe on 2018/07/27.
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
public class Util {

    public static String validateTestRequest(Context context, MHealthTestRequest testRequest) {
        if(testRequest==null)
            return "Malformed Request Object";
        else if(testRequest.getGeneratedId()==null || testRequest.getGeneratedId().length()<24)
            return "Invalid generatedId parameter";
        else {
            //TESTS PATIENT VALIDATION
             if(testRequest.getPatient()!=null) {
                 String patientValidation = testRequest.getPatient().validate(context);
                 if(patientValidation!=null)
                     return patientValidation;
             }
            //TEST returnIntentActionName PARAM
            String returnIntentActionName = testRequest.getReturnIntentActionName();
            if(returnIntentActionName==null || returnIntentActionName.length()==0)
                return "returnIntentActionName parameter required";

            //TEST requested testIndex
            int testIndex = testRequest.getTestIndex();
            if(testIndex==CODE_UNSET || testIndex==INDEX_HEARSCREEN || testIndex==INDEX_HEARTEST || testIndex==INDEX_PEEK || testIndex==INDEX_HEARSCOPE || testIndex==INDEX_SEALCHECK)
                return null; //VALIDATION PASSED
            else
                return "Invalid testIndex parameter";
        }

    }

    public static HearscreenTest buildHearScreenTestFromJson(String jsonData) {
        HearscreenTest test = HearscreenTest.fromJson(jsonData);
        test.setFrequencyResults(new Gson().fromJson(test.getFrequencyResultsJson(), HearscreenFrequencyResult[].class));
        test.setFrequencyResultsJson(null);
        return test;
    }

    public static HeartestTest buildHearTestTestFromJson(String jsonData) {
        HeartestTest test = HeartestTest.fromJson(jsonData);
        test.setFrequencyResults(new Gson().fromJson(test.getFrequencyResultsJson(), HeartestFrequencyResult[].class));
        test.setFrequencyResultsJson(null);
        return test;
    }

    public static PeekAcuityTest buildPeekAcuityTestFromJson(String jsonData) {
        return PeekAcuityTest.fromJson(jsonData);
    }
}

/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */

package com.hearxgroup.mhealthintegration;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.hearxgroup.mhealthintegration.Models.HearriskTest;
import com.hearxgroup.mhealthintegration.Models.HearscreenTest;
import com.hearxgroup.mhealthintegration.Models.HearspeechTest;
import com.hearxgroup.mhealthintegration.Models.HeartestTest;
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;

import com.hearxgroup.mhealthintegration.Models.VulaVisionTest;
import com.hearxgroup.mhealth.resources.Const;
import timber.log.Timber;

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
public class Util {

    private static int INDEX_HEARSCREEN = Const.INSTANCE.getAppIndexFromTest(Const.TEST.HEARSCREEN).getValue();
    private static int INDEX_HEARTEST = Const.INSTANCE.getAppIndexFromTest(Const.TEST.HEARTEST).getValue();
    private static int INDEX_VISION = Const.INSTANCE.getAppIndexFromTest(Const.TEST.VISION).getValue();
    private static int INDEX_HEARSPEECH = Const.INSTANCE.getAppIndexFromTest(Const.TEST.HEARSPEECH).getValue();
    private static int INDEX_HEARRISK = Const.INSTANCE.getAppIndexFromTest(Const.TEST.HEARRISK).getValue();
    private static int INDEX_HEARSCOPE = Const.INSTANCE.getAppIndexFromTest(Const.TEST.HEARSCOPE).getValue();
    private static int INDEX_SEALCHECK = Const.INSTANCE.getAppIndexFromTest(Const.TEST.SEALCHECK).getValue();

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
            Const.TEST testIndex = testRequest.getTestIndex();
            if(testIndex== null ||
                    testIndex== Const.TEST.HEARSCREEN ||
                    testIndex== Const.TEST.HEARTEST ||
                    testIndex== Const.TEST.VISION ||
                    testIndex== Const.TEST.HEARSPEECH ||
                    testIndex== Const.TEST.HEARSCOPE ||
                    testIndex== Const.TEST.SEALCHECK ||
                    testIndex== Const.TEST.HEARRISK)
                return null; //VALIDATION PASSED
            else
                return "Invalid testIndex parameter";
        }

    }

    public static HearscreenTest buildHearScreenTestFromJson(String jsonData) {
        HearscreenTest test = HearscreenTest.fromJson(jsonData);
        return test;
    }

    public static HeartestTest buildHearTestTestFromJson(String jsonData) {

        Log.d("HeartestTest build","jsonData HearTest -> %s" + jsonData);
        return HeartestTest.fromJson(jsonData);
    }

    public static HearspeechTest buildHearSpeechTestFromJson(String jsonData){
        Log.d("spee", "JsonData= " + jsonData);

        return HearspeechTest.Companion.fromJson(jsonData);
    }

    public static HearriskTest buildHearRiskTestFromJson(String jsonData){
        HearriskTest test = HearriskTest.Companion.fromJson(jsonData);

        return test;
    }

    public static VulaVisionTest buildVulaVisionTestFromJson(String jsonData) {
        Timber.d("JsonData VulaVision= " + jsonData);
        return VulaVisionTest.Companion.fromJson(jsonData);
    }
}

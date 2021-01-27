/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
package com.hearxgroup.mhealthintegration

import android.app.LoaderManager
import android.content.Context
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest
import android.os.Bundle
import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract.ContentRetrieverInterface
import com.hearxgroup.mhealthintegration.TestRetrievers.MHealthContentRetriever
import android.content.Intent
import android.util.Log
import com.hearxgroup.mhealth.resources.Const
import com.hearxgroup.mhealth.resources.Const.BUNDLE_EXTRA_MHTEST_GN_ID
import com.hearxgroup.mhealth.resources.Const.BUNDLE_EXTRA_TEST_TYPE
import com.hearxgroup.mhealth.resources.Const.PROVIDER_CODE_FACILITY
import com.hearxgroup.mhealth.resources.Const.PROVIDER_CODE_PATIENT
import com.hearxgroup.mhealth.resources.Const.PROVIDER_CODE_TEST_HEARRISK
import com.hearxgroup.mhealth.resources.Const.PROVIDER_CODE_TEST_HEARSCREEN
import com.hearxgroup.mhealth.resources.Const.PROVIDER_CODE_TEST_HEARSPEECH
import com.hearxgroup.mhealth.resources.Const.PROVIDER_CODE_TEST_HEARTEST
import com.hearxgroup.mhealth.resources.Const.PROVIDER_CODE_TEST_VISION
import com.hearxgroup.mhealth.resources.Const.getAppIndexFromTest
import com.hearxgroup.mhealthintegration.Const.BUNDLE_EXTRA_MHTEST_REQUEST_JSON
import com.hearxgroup.mhealthintegration.Const.INDEX_HEARSPEECH
import com.hearxgroup.orbit.logic.Const.CODE_UNSET

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
object TestRequestHelper {

    private val INDEX_HEARSCREEN = getAppIndexFromTest(Const.TEST.HEARSCREEN).value
    private val INDEX_HEARTEST = getAppIndexFromTest(Const.TEST.HEARTEST).value
    private val INDEX_VISION = getAppIndexFromTest(Const.TEST.VISION).value
    private val INDEX_HEARSPEECH = getAppIndexFromTest(Const.TEST.HEARSPEECH).value
    private val INDEX_HEARRISK = getAppIndexFromTest(Const.TEST.HEARRISK).value

    private val TAG = TestRequestHelper::class.java.simpleName
    fun startTest(context: Context, testRequest: MHealthTestRequest): String? {
        val validationResponse = Util.validateTestRequest(context, testRequest)
        if (validationResponse == null) {
            val bundle = Bundle()
            bundle.putString(BUNDLE_EXTRA_MHTEST_REQUEST_JSON, testRequest.toJson())
            context.startActivity(AppLinkUtil.getLaunchAppIntent(context.packageManager, bundle, "com.hearxgroup.mhealthstudio.intent.action.testrequest")) //TODO UPDATE WITH HEARX LIB
        }
        return validationResponse
    }

    fun retrieveTestResult(
            context: Context?,
            loaderManager: LoaderManager?,
            callback: ContentRetrieverInterface?,
            testIndex: Int,
            generatedId: String?) {

        var providerCode: Int = CODE_UNSET
        when (testIndex) {
            INDEX_HEARSCREEN -> providerCode = PROVIDER_CODE_TEST_HEARSCREEN
            INDEX_HEARTEST -> providerCode = PROVIDER_CODE_TEST_HEARTEST
            INDEX_VISION -> providerCode = PROVIDER_CODE_TEST_VISION
            INDEX_HEARSPEECH -> providerCode = PROVIDER_CODE_TEST_HEARSPEECH
            INDEX_HEARRISK -> providerCode = PROVIDER_CODE_TEST_HEARRISK
        }
        Log.d("PROVIDER_CODE", testIndex.toString() + " " +  providerCode.toString())
        MHealthContentRetriever(context, loaderManager, callback).startPoll(providerCode, generatedId)
    }

    fun retrievePatient(
            context: Context?,
            loaderManager: LoaderManager?,
            callback: ContentRetrieverInterface?,
            generatedId: String?) {
        MHealthContentRetriever(context, loaderManager, callback).startPoll(PROVIDER_CODE_PATIENT, generatedId)
    }

    fun retrieveFacility(
            context: Context?,
            loaderManager: LoaderManager?,
            callback: ContentRetrieverInterface?,
            generatedId: String?) {
        MHealthContentRetriever(context, loaderManager, callback).startPoll(PROVIDER_CODE_FACILITY, generatedId)
    }

    fun getGeneratedTestIdFromIntent(intent: Intent): String? {
        val bundle = intent.extras
        return if (bundle != null && bundle.getString(BUNDLE_EXTRA_MHTEST_GN_ID) != null) {
            bundle.getString(BUNDLE_EXTRA_MHTEST_GN_ID)
        } else null
    }

    fun getTestTypeFromIntent(intent: Intent): Int {
        Log.d(TAG, "getTestTypeFromIntent")
        val bundle = intent.extras
        Log.d(TAG, "GetTestTypeFromIntent_APP -> " + bundle.getInt(BUNDLE_EXTRA_TEST_TYPE))
        return if (bundle != null && bundle.getString(BUNDLE_EXTRA_MHTEST_GN_ID) != null) {
            bundle.getInt(BUNDLE_EXTRA_TEST_TYPE)
        } else -1
    }
}
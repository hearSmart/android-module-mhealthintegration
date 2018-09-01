package com.hearxgroup.mhealthintegration;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hearxgroup.hearx.MiscUtils;
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
import com.hearxgroup.mhealthintegration.TestRetrievers.MHealthContentRetriever;
import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract;

import static com.hearxgroup.hearx.Constants.BUNDLE_EXTRA_MHTEST_GN_ID;
import static com.hearxgroup.hearx.Constants.BUNDLE_EXTRA_MHTEST_REQUEST_JSON;
import static com.hearxgroup.hearx.Constants.BUNDLE_EXTRA_TEST_TYPE;
import static com.hearxgroup.hearx.Constants.CODE_UNSET;
import static com.hearxgroup.hearx.Constants.INDEX_HEARSCREEN;
import static com.hearxgroup.hearx.Constants.INDEX_HEARTEST;
import static com.hearxgroup.hearx.Constants.INDEX_PEEK;
import static com.hearxgroup.hearx.Constants.PROVIDER_CODE_FACILITY;
import static com.hearxgroup.hearx.Constants.PROVIDER_CODE_PATIENT;
import static com.hearxgroup.hearx.Constants.PROVIDER_CODE_TEST_HEARSCREEN;
import static com.hearxgroup.hearx.Constants.PROVIDER_CODE_TEST_HEARTEST;
import static com.hearxgroup.hearx.Constants.PROVIDER_CODE_TEST_PEEK;

/**
 * Copyright (c) 2017 hearX Group (Pty) Ltd. All rights reserved
 * Created by David Howe on 2018/07/27.
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
public class TestRequestHelper {

    private static final String TAG = TestRequestHelper.class.getSimpleName();

    public static String startTest(Context context, MHealthTestRequest testRequest) {
        String validationResponse = Util.validateTestRequest(context, testRequest);
        if(validationResponse==null) {
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_EXTRA_MHTEST_REQUEST_JSON, testRequest.toJson());
            context.startActivity(MiscUtils.getLaunchAppIntent(context.getPackageManager(), bundle, "com.hearxgroup.mhealthstudio.intent.action.testrequest")); //TODO UPDATE WITH HEARX LIB
        }
        return validationResponse;
    }

    public static void retrieveTestResult(
            Context context,
            LoaderManager loaderManager,
            MHealthTestRetrieverContract.ContentRetrieverInterface callback,
            int testIndex,
            String generatedId) {
        int providerCode = CODE_UNSET;
        switch(testIndex) {
            case INDEX_HEARSCREEN: providerCode = PROVIDER_CODE_TEST_HEARSCREEN; break;
            case INDEX_HEARTEST: providerCode = PROVIDER_CODE_TEST_HEARTEST; break;
            case INDEX_PEEK: providerCode = PROVIDER_CODE_TEST_PEEK; break;
        }
        new MHealthContentRetriever(context, loaderManager, callback).startPoll(providerCode, generatedId);
    }

    public static void retrievePatient(
            Context context,
            LoaderManager loaderManager,
            MHealthTestRetrieverContract.ContentRetrieverInterface callback,
            String generatedId) {
        new MHealthContentRetriever(context, loaderManager, callback).startPoll(PROVIDER_CODE_PATIENT, generatedId);
    }

    public static void retrieveFacility(
            Context context,
            LoaderManager loaderManager,
            MHealthTestRetrieverContract.ContentRetrieverInterface callback,
            String generatedId) {
        new MHealthContentRetriever(context, loaderManager, callback).startPoll(PROVIDER_CODE_FACILITY, generatedId);
    }

    public static String getGeneratedTestIdFromIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle!=null && bundle.getString(BUNDLE_EXTRA_MHTEST_GN_ID)!=null) {
            return bundle.getString(BUNDLE_EXTRA_MHTEST_GN_ID);
        }
        else
            return null;
    }

    public static int getTestTypeFromIntent(Intent intent) {
        Log.d(TAG, "getTestTypeFromIntent");
        Bundle bundle = intent.getExtras();
        if(bundle!=null && bundle.getString(BUNDLE_EXTRA_MHTEST_GN_ID)!=null) {
            return bundle.getInt(BUNDLE_EXTRA_TEST_TYPE);
        }
        else
            return -1;
    }
}

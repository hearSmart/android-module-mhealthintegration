package com.hearxgroup.mhealthintegration;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hearxgroup.hearx.MiscUtils;
import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
import com.hearxgroup.mhealthintegration.TestRetrievers.MHealthTestRetriever;
import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract;

import static com.hearxgroup.hearx.Constants.BUNDLE_EXTRA_MHTEST_GN_ID;
import static com.hearxgroup.hearx.Constants.BUNDLE_EXTRA_MHTEST_REQUEST_JSON;
import static com.hearxgroup.hearx.Constants.BUNDLE_EXTRA_TEST_TYPE;

/**
 * Copyright (c) 2017 hearX Group (Pty) Ltd. All rights reserved
 * Created by David Howe on 2018/07/27.
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
public class TestRequestHelper {

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
            MHealthTestRetrieverContract.TestRetrieverInterface callback,
            int testType,
            String testId) {
        new MHealthTestRetriever(context, loaderManager, callback).startPoll(testType, testId);
    }

    public static String getTestIdFromIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle!=null && bundle.getString(BUNDLE_EXTRA_MHTEST_GN_ID)!=null) {
            return bundle.getString(BUNDLE_EXTRA_MHTEST_GN_ID);
        }
        else
            return null;
    }

    public static int getTestTypeFromIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle!=null && bundle.getString(BUNDLE_EXTRA_MHTEST_GN_ID)!=null) {
            return bundle.getInt(BUNDLE_EXTRA_TEST_TYPE);
        }
        else
            return -1;
    }
}

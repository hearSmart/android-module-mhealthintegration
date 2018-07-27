package com.hearxgroup.mhealthintegration;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
import com.hearxgroup.mhealthintegration.TestRetrievers.MHealthTestRetriever;
import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract;

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
            bundle.putString(Constants.BUNDLE_EXTRA_MHTEST_REQUEST_JSON, testRequest.toJson());
            Util.openApp(context, bundle, Constants.REQUEST_HEARING_TEST_ACTION_NAME);
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
        if(bundle!=null && bundle.getString(Constants.BUNDLE_EXTRA_MHTEST_GN_ID)!=null) {
            return bundle.getString(Constants.BUNDLE_EXTRA_MHTEST_GN_ID);
        }
        else
            return null;
    }
}

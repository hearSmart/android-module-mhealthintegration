/*
 *
 * *
 *  Copyright (c) 2016-2018 hearX Group (Pty) Ltd. All rights reserved
 *  Contact info@hearxgroup.com
 *  Created by David Howe
 *  Last modified David Howe on 2018/02/21 10:07 PM
 * /
 */

package com.hearxgroup.mhealthintegration.TestRetrievers;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.gson.Gson;
import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract;
import com.hearxgroup.mhealthintegration.Models.HearscreenFrequencyResult;
import com.hearxgroup.mhealthintegration.Models.HearscreenTest;
import com.hearxgroup.mhealthintegration.Models.HeartestFrequencyResult;
import com.hearxgroup.mhealthintegration.Models.HeartestTest;
import com.hearxgroup.mhealthintegration.Models.PeekAcuityTest;

import static com.hearxgroup.hearx.Constants.*;

public class MHealthTestRetriever implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 1;

    private Uri contentUri;
    private LoaderManager loaderManager;
    private Context context;
    private MHealthTestRetrieverContract.TestRetrieverInterface listener;
    private int providerCode;

    public MHealthTestRetriever(Context context, LoaderManager loaderManager, MHealthTestRetrieverContract.TestRetrieverInterface listener) {
        this.context = context;
        this.loaderManager = loaderManager;
        this.listener = listener;
    }

    public void startPoll(int providerCode, String generatedId) {
        this.providerCode = providerCode;
        String provider = getProvider(providerCode);
        if(provider==null)
            listener.onRetrieveContentError("Invalid providerCode");
        else {
            contentUri = Uri.parse("content://" + provider + generatedId);
            loaderManager.initLoader(LOADER_ID, null, MHealthTestRetriever.this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(context, contentUri,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data = context.getContentResolver().query(contentUri, null, null, null, null);
        if (data != null && data.getCount() > 0) {
            data.moveToFirst();
            returnContent(providerCode, data.getString(1));
        }
        else
            listener.onRetrieveContentError("Test not found with provided testType/testId combination");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    private String getProvider(int providerCode) {
        switch(providerCode) {
            case PROVIDER_CODE_TEST_HEARSCREEN: return PROVIDER_NAME_HEARSCREEN_TEST;
            case PROVIDER_CODE_TEST_HEARTEST: return PROVIDER_NAME_HEARTEST_TEST;
            case PROVIDER_CODE_TEST_PEEK: return PROVIDER_NAME_PEEKACUITY_TEST;
            case PROVIDER_CODE_PATIENT: return PROVIDER_NAME_PATIENT;
            case PROVIDER_CODE_FACILITY: return PROVIDER_NAME_FACILITY;
            default : return null;
        }
    }

    private void returnContent(int testType, String testJson) {
        switch(testType) {
            case PROVIDER_CODE_TEST_HEARSCREEN:
                if(testJson!=null && testJson.length()>0) {
                    HearscreenTest test = HearscreenTest.fromJson(testJson);
                    test.setFrequencyResults(new Gson().fromJson(test.getFrequencyResultsJson(), HearscreenFrequencyResult[].class));
                    test.setFrequencyResultsJson(null);
                    listener.onRetrieveTestHearScreen(test);
                }
                break;
            case PROVIDER_CODE_TEST_HEARTEST:
                if(testJson!=null && testJson.length()>0) {
                    HeartestTest test = HeartestTest.fromJson(testJson);
                    test.setFrequencyResults(new Gson().fromJson(test.getFrequencyResultsJson(), HeartestFrequencyResult[].class));
                    test.setFrequencyResultsJson(null);
                    listener.onRetrieveTestHearTest(test);
                }
                break;
            case PROVIDER_CODE_TEST_PEEK:
                if(testJson!=null && testJson.length()>0) {
                    PeekAcuityTest test = PeekAcuityTest.fromJson(testJson);
                    listener.onRetrieveTestPeekAcuity(test);
                }
                break;
        }
    }

}

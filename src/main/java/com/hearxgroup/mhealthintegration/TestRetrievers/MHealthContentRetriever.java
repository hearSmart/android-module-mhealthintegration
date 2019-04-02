/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */

package com.hearxgroup.mhealthintegration.TestRetrievers;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract;
import com.hearxgroup.mhealthintegration.Models.Facility;
import com.hearxgroup.mhealthintegration.Models.HearscreenFrequencyResult;
import com.hearxgroup.mhealthintegration.Models.HearscreenTest;
import com.hearxgroup.mhealthintegration.Models.HeartestFrequencyResult;
import com.hearxgroup.mhealthintegration.Models.HeartestTest;
import com.hearxgroup.mhealthintegration.Models.Patient;
import com.hearxgroup.mhealthintegration.Models.PeekAcuityTest;
import com.hearxgroup.mhealthintegration.TestRequestHelper;
import com.hearxgroup.mhealthintegration.Util;

import static com.hearxgroup.hearx.Constants.*;

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
public class MHealthContentRetriever implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = MHealthContentRetriever.class.getSimpleName();

    private static final int LOADER_ID = 1;

    private Uri contentUri;
    private LoaderManager loaderManager;
    private Context context;
    private MHealthTestRetrieverContract.ContentRetrieverInterface listener;
    private int providerCode;

    public MHealthContentRetriever(Context context, LoaderManager loaderManager, MHealthTestRetrieverContract.ContentRetrieverInterface listener) {
        this.context = context;
        this.loaderManager = loaderManager;
        this.listener = listener;
    }

    public void startPoll(int providerCode, String generatedId) {
        Log.d(TAG, "startPoll");
        Log.d(TAG, "providerCode:"+providerCode);
        Log.d(TAG, "generatedId:"+generatedId);
        this.providerCode = providerCode;
        String provider = getProvider(providerCode);
        if(provider==null)
            listener.onRetrieveContentError("Invalid providerCode");
        else {
            String uriString = "content://" + provider + generatedId;
            Log.d(TAG, "uriString:"+uriString);
            contentUri = Uri.parse(uriString);
            loaderManager.initLoader(LOADER_ID, null, MHealthContentRetriever.this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        return new CursorLoader(context, contentUri,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished");
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
        Log.d(TAG, "onLoaderReset");
    }

    private String getProvider(int providerCode) {
        Log.d(TAG, "getProvider");
        Log.d(TAG, "providerCode:"+providerCode);
        switch(providerCode) {
            case PROVIDER_CODE_TEST_HEARSCREEN: return PROVIDER_NAME_HEARSCREEN_TEST;
            case PROVIDER_CODE_TEST_HEARTEST: return PROVIDER_NAME_HEARTEST_TEST;
            case PROVIDER_CODE_TEST_PEEK: return PROVIDER_NAME_PEEKACUITY_TEST;
            case PROVIDER_CODE_PATIENT: return PROVIDER_NAME_PATIENT;
            case PROVIDER_CODE_FACILITY: return PROVIDER_NAME_FACILITY;
            default : return null;
        }
    }

    private void returnContent(int testType, String dataJson) {
        switch(testType) {
            case PROVIDER_CODE_TEST_HEARSCREEN:
                if(dataJson!=null && dataJson.length()>0) {
                    listener.onRetrieveTestHearScreen(Util.buildHearScreenTestFromJson(dataJson));
                }
                break;
            case PROVIDER_CODE_TEST_HEARTEST:
                if(dataJson!=null && dataJson.length()>0) {
                    listener.onRetrieveTestHearTest(Util.buildHearTestTestFromJson(dataJson));
                }
                break;
            case PROVIDER_CODE_TEST_PEEK:
                if(dataJson!=null && dataJson.length()>0) {
                    listener.onRetrieveTestPeekAcuity(Util.buildPeekAcuityTestFromJson(dataJson));
                }
                break;
            case PROVIDER_CODE_PATIENT:
                if(dataJson!=null && dataJson.length()>0) {
                    Patient patient = Patient.fromJson(dataJson);
                    listener.onRetrievePatient(patient);
                }
                break;
            case PROVIDER_CODE_FACILITY:
                if(dataJson!=null && dataJson.length()>0) {
                    Facility facility = Facility.fromJson(dataJson);
                    listener.onRetrieveFacility(facility);
                }
                break;
        }
    }

}

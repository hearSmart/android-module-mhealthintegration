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

import com.hearxgroup.mhealth.resources.Const;
import com.hearxgroup.mhealthintegration.Contracts.MHealthTestRetrieverContract;
import com.hearxgroup.mhealthintegration.Models.Facility;
import com.hearxgroup.mhealthintegration.Models.Patient;
import com.hearxgroup.mhealthintegration.Util;

import timber.log.Timber;

import static com.hearxgroup.mhealth.resources.Const.PROVIDER_DATA_FOUND;
import static com.hearxgroup.mhealth.resources.Const.PROVIDER_DATA_NOT_FOUND;

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
            //provider = com.hearxgroup.mhealthstudio.ContentProviderTestHearTest
            String uriString = "content://" + provider + "/uuid/" + generatedId;
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
        //data = context.getContentResolver().query(contentUri, null, null, null, null);
        if (data != null && data.getCount() > 0) {
            data.moveToFirst();
            boolean testNotFound = data.getString(1).equalsIgnoreCase(PROVIDER_DATA_NOT_FOUND);
            if(testNotFound){
                Log.d(TAG,"TestNotFound");
            }
            else{
                Log.d(TAG,"TestWasFound");
                data.moveToNext();
                returnContent(providerCode, data.getString(1));
            }


        }
        else{
            listener.onRetrieveContentError("Test not found with provided testType/testId combination");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset");
    }

    private String getProvider(int providerCode) {
        Log.d(TAG, "getProvider");
        Log.d(TAG, "providerCode:"+providerCode);
        Log.d(TAG, "PROVIDER_CODE_GET_PROVIDER -> " + String.valueOf(providerCode));
        switch(providerCode) {
            case Const.PROVIDER_CODE_TEST_HEARSCREEN: return Const.PROVIDER_NAME_TEST_HEARSCREEN;
            case Const.PROVIDER_CODE_TEST_HEARTEST: return Const.PROVIDER_NAME_TEST_HEARTEST;
            case Const.PROVIDER_CODE_TEST_VISION: return Const.PROVIDER_NAME_TEST_VISION;
            case Const.PROVIDER_CODE_PATIENT: return Const.PROVIDER_NAME_PATIENT;
            case Const.PROVIDER_CODE_FACILITY: return Const.PROVIDER_NAME_FACILITY;
            case Const.PROVIDER_CODE_TEST_HEARSPEECH: return Const.PROVIDER_NAME_TEST_HEARSPEECH;
            case Const.PROVIDER_CODE_TEST_HEARRISK: return Const.PROVIDER_NAME_TEST_HEARRISK;
            default : return null;
        }
    }

    private void returnContent(int testType, String dataJson) {
        //Timber.d("testType / dataJson ->" + testType +" / " + dataJson);
        Log.d(TAG, "testType " + testType + " / dataJson" + dataJson);
        switch(testType) {
            case Const.PROVIDER_CODE_TEST_HEARSCREEN:
                if(dataJson!=null && dataJson.length()>0) {
                    listener.onRetrieveTestHearScreen(Util.buildHearScreenTestFromJson(dataJson));
                }
                break;
            case Const.PROVIDER_CODE_TEST_HEARTEST:
                if(dataJson!=null && dataJson.length()>0) {
                    listener.onRetrieveTestHearTest(Util.buildHearTestTestFromJson(dataJson));
                }
                break;
            case Const.PROVIDER_CODE_TEST_HEARSPEECH:
                if(dataJson!=null && dataJson.length()>0){
                    listener.onRetrieveTestHearSpeech(Util.buildHearSpeechTestFromJson(dataJson));
                }
                break;
            case Const.PROVIDER_CODE_TEST_HEARRISK:
                if (dataJson != null && dataJson.length() > 0) {
                    listener.onRetrieveTestHearRisk(Util.buildHearRiskTestFromJson(dataJson));
                }
                break;
            case Const.PROVIDER_CODE_TEST_VISION:
                if(dataJson!=null && dataJson.length()>0) {
                    listener.onRetrieveTestVulaVision(Util.buildVulaVisionTestFromJson(dataJson));
                }
                break;
            case  Const.PROVIDER_CODE_PATIENT:
                if(dataJson!=null && dataJson.length()>0) {
                    Patient patient = Patient.fromJson(dataJson);
                    listener.onRetrievePatient(patient);
                }
                break;
            case Const.PROVIDER_CODE_FACILITY:
                if(dataJson!=null && dataJson.length()>0) {
                    Facility facility = Facility.fromJson(dataJson);
                    listener.onRetrieveFacility(facility);
                }
                break;
        }
    }

}

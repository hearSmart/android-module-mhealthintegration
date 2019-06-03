package com.hearxgroup.mhealthintegration;

import android.app.LoaderManager;
import android.content.Context;

import com.hearxgroup.mhealthintegration.Contracts.MHealthStateRetrieverContract;

public class MHealthStateHelper {

    private static final String TAG = MHealthStateHelper.class.getSimpleName();

    public enum HEADPHONE_STATE {
        UNKNOWN, UNAUTHENTICATED, UNLINKED, UNVERIFIED, OK
    }

    public enum DEVICE_STATE {
        UNKNOWN, UNAUTHENTICATED, UNSTANDARDISED, UNSYNCED, OK
    }

    public enum APP_STATE {
        UNKNOWN, UNAUTHENTICATED, NOT_INSTALLED, REQUIRES_UPDATE, CAN_UPDATE, REQUIRES_LICENSE, OK
    }

    public static void retrieveHeadphoneState(
            Context context,
            LoaderManager loaderManager,
            MHealthStateRetrieverContract.ContentRetrieverInterface callback) {
        //todo
    }

    public static void retrieveDeviceState(
            Context context,
            LoaderManager loaderManager,
            MHealthStateRetrieverContract.ContentRetrieverInterface callback) {
        //todo
    }

    public static void retrieveAppState(
            Context context,
            LoaderManager loaderManager,
            MHealthStateRetrieverContract.ContentRetrieverInterface callback,
            int testIndex) {
        //todo
    }




}

package com.hearxgroup.mhealthintegration;

import android.app.LoaderManager;
import android.content.Context;

import com.hearxgroup.mhealthintegration.Contracts.MHealthStateRetrieverContract;

public class MHealthStateHelper {

    private static final String TAG = MHealthStateHelper.class.getSimpleName();

    public enum HEADPHONE_STATE {
        UNKNOWN, UNAUTHENTICATED, UNLINKED, UNVERIFIED, UNCALIBRATED, OK
    }

    public enum DEVICE_STATE {
        UNKNOWN, UNAUTHENTICATED, UNSTANDARDISED, UNSYNCED, OK
    }

    public enum APP_STATE {
        UNKNOWN, UNAUTHENTICATED, NOT_INSTALLED, UPDATE_AVAILABLE, UPDATE_REQUIRED, OK
    }

    public enum LICENSE_STATE {
        UNKNOWN, UNAUTHENTICATED, UNLICENSED, OK
    }

    public enum UPDATE_STATE {
        UNKNOWN, UNAUTHENTICATED, AVAILABLE_ONLINE, AVAILABLE_OFFLINE, NOT_REQUIRED
    }

    /**
     * Method used to request the status of the currently linked headphone
     * @param context
     * @param loaderManager
     * @param callback
     */
    public static void retrieveHeadphoneState(
            Context context,
            LoaderManager loaderManager,
            MHealthStateRetrieverContract.ContentRetrieverInterface callback) {
        //todo

    }

    /**
     * Method used to request the status of the mHealth device
     * @param context
     * @param loaderManager
     * @param callback
     */
    public static void retrieveDeviceState(
            Context context,
            LoaderManager loaderManager,
            MHealthStateRetrieverContract.ContentRetrieverInterface callback) {
        //todo

    }

    /**
     * Method used to request the status of status of a given app
     * @param context
     * @param loaderManager
     * @param callback
     * @param testIndex Eg. INDEX_HEARSCREEN
     */
    public static void retrieveAppState(
            Context context,
            LoaderManager loaderManager,
            MHealthStateRetrieverContract.ContentRetrieverInterface callback,
            int testIndex) {

        //todo
    }

    /**
     * Method used to request the license status of a given app
     * @param context
     * @param loaderManager
     * @param callback
     * @param testIndex Eg. INDEX_HEARSCREEN
     */
    public static void retrieveLicenseState(
            Context context,
            LoaderManager loaderManager,
            MHealthStateRetrieverContract.ContentRetrieverInterface callback,
            int testIndex) {

        //todo
    }

    /**
     * Method used to request the update status of a given app
     * @param context
     * @param loaderManager
     * @param callback
     * @param testIndex Eg. INDEX_HEARSCREEN
     */
    public static void retrieveUpdateState(
            Context context,
            LoaderManager loaderManager,
            MHealthStateRetrieverContract.ContentRetrieverInterface callback,
            int testIndex) {

        //todo
    }




}

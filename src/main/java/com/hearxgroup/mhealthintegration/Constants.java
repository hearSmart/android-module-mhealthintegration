package com.hearxgroup.mhealthintegration;

/**
 * Copyright (c) 2017 hearX Group (Pty) Ltd. All rights reserved
 * Created by David Howe on 2018/07/27.
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
public class Constants {
    public static final String REQUEST_HEARING_TEST_ACTION_NAME = "com.hearxgroup.mhealthstudio.intent.action.requesthearingtest";

    public static final String BUNDLE_EXTRA_MHTEST_REQUEST_JSON = "mhealthstudio.BUNDLE_EXTRA_MHTEST_REQUEST_JSON";
    public static final String BUNDLE_EXTRA_MHTEST_GN_ID = "BUNDLE_EXTRA_HT_TEST_GN_ID";

    public static final String TEXT_GENDER_FEMALE = "female";
    public static final String TEXT_GENDER_MALE = "male";

    public static final int INDEX_HEARSCREEN = 1;
    public static final int INDEX_HEARTEST = 2;
    public static final int INDEX_PEEK = 3;

    public static final int HEARSCREEN_SEVERITY_NOT_TESTED = -1;
    public static final int HEARSCREEN_SEVERITY_NONE = 0;
    public static final int HEARSCREEN_SEVERITY_SLIGHT = 1;
    public static final int HEARSCREEN_SEVERITY_MILD = 2;
    public static final int HEARSCREEN_SEVERITY_MODERATE = 3;
    public static final int HEARSCREEN_SEVERITY_MODERATE_SEVERE = 4;
    public static final int HEARSCREEN_SEVERITY_SEVERE = 5;

    public static final int EAR_LEFT = 1;
    public static final int EAR_RIGHT = 2;
}

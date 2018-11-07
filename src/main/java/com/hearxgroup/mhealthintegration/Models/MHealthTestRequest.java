package com.hearxgroup.mhealthintegration.Models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (c) 2017 hearX Group (Pty) Ltd. All rights reserved
 * Created by David Howe on 2018/07/27.
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
@Getter
@Setter
public class MHealthTestRequest {
    @NonNull
    private String generatedId; //24 character length randomly generated string *NB REQUIRED
    @Nullable
    private Patient patient;
    @NonNull
    private String returnIntentActionName; //Return intent filter action name
    @NonNull
    private int testIndex; //use CODE_UNSET if no specific test required

    public static MHealthTestRequest build(
            @NonNull String generatedId,
            @NonNull String returnIntentActionName,
            @Nullable Patient patient,
            @NonNull int testIndex) {
        MHealthTestRequest mHealthTestRequest = new MHealthTestRequest();
        mHealthTestRequest.setGeneratedId(generatedId);
        mHealthTestRequest.setReturnIntentActionName(returnIntentActionName);
        mHealthTestRequest.setPatient(patient);
        mHealthTestRequest.setTestIndex(testIndex);
        return mHealthTestRequest;
    }

    public static MHealthTestRequest fromJson(String json) {
        return new Gson().fromJson(json, MHealthTestRequest.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}

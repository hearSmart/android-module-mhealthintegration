package com.hearxgroup.mhealthintegration.Models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

/**
 * Copyright (c) 2017 hearX Group (Pty) Ltd. All rights reserved
 * Created by David Howe on 2018/07/27.
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
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

    @NonNull
    public String getGeneratedId() {
        return generatedId;
    }

    public void setGeneratedId(@NonNull String generatedId) {
        this.generatedId = generatedId;
    }

    @Nullable
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(@Nullable Patient patient) {
        this.patient = patient;
    }

    @NonNull
    public String getReturnIntentActionName() {
        return returnIntentActionName;
    }

    public void setReturnIntentActionName(@NonNull String returnIntentActionName) {
        this.returnIntentActionName = returnIntentActionName;
    }

    public int getTestIndex() {
        return testIndex;
    }

    public void setTestIndex(int testIndex) {
        this.testIndex = testIndex;
    }
}

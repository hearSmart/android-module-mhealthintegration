package com.hearxgroup.mhealthintegration.Models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.hearxgroup.mhealthintegration.Constants;

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
    //TODO testType
    /*private int testType; //hearScreen/hearTest/hearDin/hearScope/Peek Acuity.*/
    @NonNull
    private String generatedId; //24 character length randomly generated string *NB REQUIRED
    @Nullable
    private Patient patient;
    @NonNull
    private String returnIntentActionName; //Return intent filter action name

    public static MHealthTestRequest build(
            @NonNull String generatedId,
            @Nullable Patient patient) {
        MHealthTestRequest mHealthTestRequest = new MHealthTestRequest();
        mHealthTestRequest.setGeneratedId(generatedId);
        mHealthTestRequest.setPatient(patient);
        mHealthTestRequest.setReturnIntentActionName(Constants.MHEALTH_ACTION_NAME);
        return mHealthTestRequest;
    }

    public static MHealthTestRequest fromJson(String json) {
        return new Gson().fromJson(json, MHealthTestRequest.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    //TODO VALIDATION CODE

}

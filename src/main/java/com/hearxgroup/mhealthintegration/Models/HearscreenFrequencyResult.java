package com.hearxgroup.mhealthintegration.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Copyright (c) 2017 hearX Group (Pty) Ltd. All rights reserved
 * Created by David Howe on 2018/03/02.
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
public class HearscreenFrequencyResult //Class to hold info for each test conducted
{
    private int intensity;
    private int frequency;
    private int ear; //0 for left, 1 for right
    private int noise; // in dB
    private boolean retest=false;
    @SerializedName("patient_heard")
    private boolean patientHeard;
    @SerializedName("condition_tone")
    private boolean conditionTone;
    private int severity;

    public HearscreenFrequencyResult(){}

    public boolean isPatientHeard() {
        return patientHeard;
    }

    public void setPatientHeard(boolean patientHeard) {
        this.patientHeard = patientHeard;
    }

    public boolean isConditionTone() {
        return conditionTone;
    }

    public void setConditionTone(boolean conditionTone) {
        this.conditionTone = conditionTone;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getEar() {
        return ear;
    }

    public void setEar(int ear) {
        this.ear = ear;
    }

    public int getNoise() {
        return noise;
    }

    public void setNoise(int noise) {
        this.noise = noise;
    }

    public boolean isRetest() {
        return retest;
    }

    public void setRetest(boolean retest) {
        this.retest = retest;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public static HearscreenFrequencyResult fromJson(String json) {
        return new Gson().fromJson(json, HearscreenFrequencyResult.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}

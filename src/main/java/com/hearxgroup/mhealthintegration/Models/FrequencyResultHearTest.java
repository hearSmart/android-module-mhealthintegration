/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */

package com.hearxgroup.mhealthintegration.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
public class FrequencyResultHearTest //Class to hold info for each test conducted
{
    private int frequency;
    private int ear;
    private int threshold;
    private int noise;
    @SerializedName("noise_5db_less")
    private int noise5DbLess;
    @SerializedName("no_response")
    private boolean noResponse;
    @SerializedName("condition_tone")
    private boolean conditionTone;
    private boolean masked;

    public FrequencyResultHearTest(){}

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

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getNoise() {
        return noise;
    }

    public void setNoise(int noise) {
        this.noise = noise;
    }

    public int getNoise5DbLess() {
        return noise5DbLess;
    }

    public void setNoise5DbLess(int noise5DbLess) {
        this.noise5DbLess = noise5DbLess;
    }

    public boolean isNoResponse() {
        return noResponse;
    }

    public void setNoResponse(boolean noResponse) {
        this.noResponse = noResponse;
    }

    public boolean isConditionTone() {
        return conditionTone;
    }

    public void setConditionTone(boolean conditionTone) {
        this.conditionTone = conditionTone;
    }

    public boolean isMasked() {
        return masked;
    }

    public void setMasked(boolean masked) {
        this.masked = masked;
    }

    public static FrequencyResultHearTest fromJson(String json) {
        return new Gson().fromJson(json, FrequencyResultHearTest.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}

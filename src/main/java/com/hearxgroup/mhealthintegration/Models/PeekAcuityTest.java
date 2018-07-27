/*
 *
 * *
 *  Copyright (c) 2016-2018 hearX Group (Pty) Ltd. All rights reserved
 *  Contact info@hearxgroup.com
 *  Created by David Howe
 *  Last modified David Howe on 2018/02/21 10:49 AM
 * /
 */

package com.hearxgroup.mhealthintegration.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Copyright (c) 2017 hearX Group (Pty) Ltd. All rights reserved
 * Created by David Howe on 2018/07/27.
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
public class PeekAcuityTest {

    //TEST IDs
    @SerializedName("local_id")
    private Long id;
    @SerializedName("id")
    private Long serverId;
    @SerializedName("generated_id")
    private String generatedId;
    //PATIENT IDs
    @SerializedName("generated_patient_id")
    private String generatedPatientId;
    @SerializedName("patient_id")
    private Long serverPatientId;
    //FACILITY IDs
    @SerializedName("generated_facility_id")
    private String generatedFacilityId;
    @SerializedName("facility_id")
    private Long serverFacilityId;
    //SERVER MEMBER ID
    @SerializedName("member_id")
    private Long serverMemberId;
    //SERVER DEVICE ID
    @SerializedName("device_id")
    private Long serverDeviceId;
    //SERVER HEADSET ID
    @SerializedName("headset_id")
    private Long serverHeadsetId;
    //LOCATION INFO
    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;
    @SerializedName("address")
    private String address;
    //MISC INFO
    @SerializedName("known_impairment_type")
    private int knownImpairmentType;
    @SerializedName("glasses")
    private boolean glasses;
    @SerializedName("pending_in_test")
    private boolean pendingInTest;
    //POPULATED FROM PEEKACUITY APP
    @SerializedName("protocol")
    private String protocol;
    @SerializedName("progression")
    private int progression;
    @SerializedName("result_right")
    private double resultRight;
    @SerializedName("result_left")
    private double resultLeft;
    @SerializedName("ignored_value")
    private double resultBoth;
    @SerializedName("average_brightness")
    private double averageBrightness;
    @SerializedName("distance")
    private double distance;

    public static PeekAcuityTest fromJson(String json) {
        return new Gson().fromJson(json, PeekAcuityTest.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getGeneratedId() {
        return generatedId;
    }

    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }

    public String getGeneratedPatientId() {
        return generatedPatientId;
    }

    public void setGeneratedPatientId(String generatedPatientId) {
        this.generatedPatientId = generatedPatientId;
    }

    public Long getServerPatientId() {
        return serverPatientId;
    }

    public void setServerPatientId(Long serverPatientId) {
        this.serverPatientId = serverPatientId;
    }

    public String getGeneratedFacilityId() {
        return generatedFacilityId;
    }

    public void setGeneratedFacilityId(String generatedFacilityId) {
        this.generatedFacilityId = generatedFacilityId;
    }

    public Long getServerFacilityId() {
        return serverFacilityId;
    }

    public void setServerFacilityId(Long serverFacilityId) {
        this.serverFacilityId = serverFacilityId;
    }

    public Long getServerMemberId() {
        return serverMemberId;
    }

    public void setServerMemberId(Long serverMemberId) {
        this.serverMemberId = serverMemberId;
    }

    public Long getServerDeviceId() {
        return serverDeviceId;
    }

    public void setServerDeviceId(Long serverDeviceId) {
        this.serverDeviceId = serverDeviceId;
    }

    public Long getServerHeadsetId() {
        return serverHeadsetId;
    }

    public void setServerHeadsetId(Long serverHeadsetId) {
        this.serverHeadsetId = serverHeadsetId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getKnownImpairmentType() {
        return knownImpairmentType;
    }

    public void setKnownImpairmentType(int knownImpairmentType) {
        this.knownImpairmentType = knownImpairmentType;
    }

    public boolean isGlasses() {
        return glasses;
    }

    public void setGlasses(boolean glasses) {
        this.glasses = glasses;
    }

    public boolean isPendingInTest() {
        return pendingInTest;
    }

    public void setPendingInTest(boolean pendingInTest) {
        this.pendingInTest = pendingInTest;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getProgression() {
        return progression;
    }

    public void setProgression(int progression) {
        this.progression = progression;
    }

    public double getResultRight() {
        return resultRight;
    }

    public void setResultRight(double resultRight) {
        this.resultRight = resultRight;
    }

    public double getResultLeft() {
        return resultLeft;
    }

    public void setResultLeft(double resultLeft) {
        this.resultLeft = resultLeft;
    }

    public double getResultBoth() {
        return resultBoth;
    }

    public void setResultBoth(double resultBoth) {
        this.resultBoth = resultBoth;
    }

    public double getAverageBrightness() {
        return averageBrightness;
    }

    public void setAverageBrightness(double averageBrightness) {
        this.averageBrightness = averageBrightness;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

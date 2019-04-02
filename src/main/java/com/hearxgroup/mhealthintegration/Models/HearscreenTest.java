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
public class HearscreenTest {

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
    @SerializedName("pending_in_test")
    private boolean pendingInTest;
    //POPULATED FROM HEARSCREEN APP
    @SerializedName("result")
    private int result;
    @SerializedName("result_impairment")
    private int resultImpairment;
    @SerializedName("protocol")
    private String protocol;
    @SerializedName("duration")
    private int duration;
    @SerializedName("software_version")
    private String softwareVersion;
    @SerializedName("notes")
    private String notes;
    @SerializedName("quality_test")
    private boolean qualityTest;
    @SerializedName("test_date")
    private Long testDate;
    @SerializedName("frequency_results_json")
    private String frequencyResultsJson; //HearscreenFrequencyResult[]
    @SerializedName("frequency_results")
    private HearscreenFrequencyResult[] frequencyResults;

    public static HearscreenTest fromJson(String json) {
        return new Gson().fromJson(json, HearscreenTest.class);
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

    public boolean isPendingInTest() {
        return pendingInTest;
    }

    public void setPendingInTest(boolean pendingInTest) {
        this.pendingInTest = pendingInTest;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResultImpairment() {
        return resultImpairment;
    }

    public void setResultImpairment(int resultImpairment) {
        this.resultImpairment = resultImpairment;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isQualityTest() {
        return qualityTest;
    }

    public void setQualityTest(boolean qualityTest) {
        this.qualityTest = qualityTest;
    }

    public Long getTestDate() {
        return testDate;
    }

    public void setTestDate(Long testDate) {
        this.testDate = testDate;
    }

    public String getFrequencyResultsJson() {
        return frequencyResultsJson;
    }

    public void setFrequencyResultsJson(String frequencyResultsJson) {
        this.frequencyResultsJson = frequencyResultsJson;
    }

    public HearscreenFrequencyResult[] getFrequencyResults() {
        return frequencyResults;
    }

    public void setFrequencyResults(HearscreenFrequencyResult[] frequencyResults) {
        this.frequencyResults = frequencyResults;
    }
}

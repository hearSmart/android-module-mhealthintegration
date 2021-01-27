/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */

package com.hearxgroup.mhealthintegration.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.hearxgroup.mhealth.resources.Const;

import java.util.List;

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
public class HearscreenTest {

    private int version;
    //TEST IDs
    @SerializedName("id")
    private Long id;
    @SerializedName("server_id")
    private Long serverId;

    @SerializedName("adminId")
    private Long adminId;
    @SerializedName("uuid")
    private String uuid;

    //PATIENT IDs
    @SerializedName("patient_uuid")
    private String patientUuid;

    @SerializedName("patient_id")
    private Long serverPatientId;

    //FACILITY IDs
    @SerializedName("facility_uuid")
    private String facilityUuid;

    @SerializedName("facility_id")
    private Long serverFacilityId;

    //SERVER MEMBER ID
    @SerializedName("memberId")
    private Long serverMemberId;
    //SERVER DEVICE ID

    //SERVER HEADSET ID
    @SerializedName("headset_id")
    private Long serverHeadsetId;

    @SerializedName("license_ids")
    private List<Long> licenseIds;

    @SerializedName("headset_cal_id")
    private Long headsetCalId;

    @SerializedName("device_std_id")
    private Long deviceStdId;

    //LOCATION INFO
    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    @SerializedName("mhealth_version")
    private int mHealthVersion;

    @SerializedName("created_at")
    private Long createdAt;

    @SerializedName("pending_upload")
    private Boolean pendingUpload;

    @SerializedName("pending_retrieval")
    private Boolean pendingRetrieval;

    @SerializedName("known_impairment_type")
    private int knownImpairmentType;

    @SerializedName("patient_job_title")
    private String patientJobTitle;

    @SerializedName("patient_company")
    private String patientCompany;

    @SerializedName("patient_employment_date")
    private String patientEmploymentDate;

    @SerializedName("patient_department")
    private String patientDepartment;

    @SerializedName("patient_noise_exposure")
    private String patientNoiseExposure;

    @SerializedName("mhealth_device_mode")
    private int mHealthDeviceMode;

    @SerializedName("terms_accepted")
    private boolean termsAccepted;

    @SerializedName("terms_url")
    private String termsUrl;

    //POPULATED FROM HEARSCREEN APP

    @SerializedName("test_date")
    private Long testDate;

    @SerializedName("protocol_obj")
    private ProtocolHearScreen protocolObj;

    @SerializedName("test_type")
    private Const.HEARSCREEN_TEST_TYPE testType;

    @SerializedName("patient_signature")
    private String patientSignature;

    @SerializedName("duration")
    private int duration;

    @SerializedName("notes")
    private String notes;

    @SerializedName("software_version")
    private String softwareVersion;

    @SerializedName("otoscopy_completed")
    private Boolean otoscopyCompleted;

    @SerializedName("otoscopy_uuid")
    private String otoscopyUuid;

    @SerializedName("result_impairment")
    private int resultImpairment;

    @SerializedName("result")
    private int result;

    @SerializedName("quality_test")
    private boolean qualityTest;

    @SerializedName("operation_mode")
    private int operationMode;

    @SerializedName("frequency_results")
    private FrequencyResultHearScreen[] frequencyResults;

    @SerializedName("intest_flags")
    private List<Const.INTEST_WARNING_FLAG> intestFlags;

    @SerializedName("state")
    private int state;

    @SerializedName("retest")
    private Boolean retest;


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

    public Long getServerPatientId() {
        return serverPatientId;
    }

    public void setServerPatientId(Long serverPatientId) {
        this.serverPatientId = serverPatientId;
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

    public int getKnownImpairmentType() {
        return knownImpairmentType;
    }

    public void setKnownImpairmentType(int knownImpairmentType) {
        this.knownImpairmentType = knownImpairmentType;
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

    public FrequencyResultHearScreen[] getFrequencyResults() {
        return frequencyResults;
    }

    public void setFrequencyResults(FrequencyResultHearScreen[] frequencyResults) {
        this.frequencyResults = frequencyResults;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPatientUuid() {
        return patientUuid;
    }

    public void setPatientUuid(String patientUuid) {
        this.patientUuid = patientUuid;
    }

    public String getFacilityUuid() {
        return facilityUuid;
    }

    public void setFacilityUuid(String facilityUuid) {
        this.facilityUuid = facilityUuid;
    }

    public List<Long> getLicenseIds() {
        return licenseIds;
    }

    public void setLicenseIds(List<Long> licenseIds) {
        this.licenseIds = licenseIds;
    }

    public Long getHeadsetCalId() {
        return headsetCalId;
    }

    public void setHeadsetCalId(Long headsetCalId) {
        this.headsetCalId = headsetCalId;
    }

    public Long getDeviceStdId() {
        return deviceStdId;
    }

    public void setDeviceStdId(Long deviceStdId) {
        this.deviceStdId = deviceStdId;
    }

    public int getmHealthVersion() {
        return mHealthVersion;
    }

    public void setmHealthVersion(int mHealthVersion) {
        this.mHealthVersion = mHealthVersion;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getPendingUpload() {
        return pendingUpload;
    }

    public void setPendingUpload(Boolean pendingUpload) {
        this.pendingUpload = pendingUpload;
    }

    public Boolean getPendingRetrieval() {
        return pendingRetrieval;
    }

    public void setPendingRetrieval(Boolean pendingRetrieval) {
        this.pendingRetrieval = pendingRetrieval;
    }

    public String getPatientJobTitle() {
        return patientJobTitle;
    }

    public void setPatientJobTitle(String patientJobTitle) {
        this.patientJobTitle = patientJobTitle;
    }

    public String getPatientCompany() {
        return patientCompany;
    }

    public void setPatientCompany(String patientCompany) {
        this.patientCompany = patientCompany;
    }

    public String getPatientEmploymentDate() {
        return patientEmploymentDate;
    }

    public void setPatientEmploymentDate(String patientEmploymentDate) {
        this.patientEmploymentDate = patientEmploymentDate;
    }

    public String getPatientDepartment() {
        return patientDepartment;
    }

    public void setPatientDepartment(String patientDepartment) {
        this.patientDepartment = patientDepartment;
    }

    public String getPatientNoiseExposure() {
        return patientNoiseExposure;
    }

    public void setPatientNoiseExposure(String patientNoiseExposure) {
        this.patientNoiseExposure = patientNoiseExposure;
    }

    public int getmHealthDeviceMode() {
        return mHealthDeviceMode;
    }

    public void setmHealthDeviceMode(int mHealthDeviceMode) {
        this.mHealthDeviceMode = mHealthDeviceMode;
    }

    public boolean isTermsAccepted() {
        return termsAccepted;
    }

    public void setTermsAccepted(boolean termsAccepted) {
        this.termsAccepted = termsAccepted;
    }

    public String getTermsUrl() {
        return termsUrl;
    }

    public void setTermsUrl(String termsUrl) {
        this.termsUrl = termsUrl;
    }

    public ProtocolHearScreen getProtocolObj() {
        return protocolObj;
    }

    public void setProtocolObj(ProtocolHearScreen protocolObj) {
        this.protocolObj = protocolObj;
    }

    public Const.HEARSCREEN_TEST_TYPE getTestType() {
        return testType;
    }

    public void setTestType(Const.HEARSCREEN_TEST_TYPE testType) {
        this.testType = testType;
    }

    public String getPatientSignature() {
        return patientSignature;
    }

    public void setPatientSignature(String patientSignature) {
        this.patientSignature = patientSignature;
    }

    public Boolean getOtoscopyCompleted() {
        return otoscopyCompleted;
    }

    public void setOtoscopyCompleted(Boolean otoscopyCompleted) {
        this.otoscopyCompleted = otoscopyCompleted;
    }

    public String getOtoscopyUuid() {
        return otoscopyUuid;
    }

    public void setOtoscopyUuid(String otoscopyUuid) {
        this.otoscopyUuid = otoscopyUuid;
    }

    public int getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(int operationMode) {
        this.operationMode = operationMode;
    }

    public List<Const.INTEST_WARNING_FLAG> getIntestFlags() {
        return intestFlags;
    }

    public void setIntestFlags(List<Const.INTEST_WARNING_FLAG> intestFlags) {
        this.intestFlags = intestFlags;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Boolean getRetest() {
        return retest;
    }

    public void setRetest(Boolean retest) {
        this.retest = retest;
    }
}

/*

    @SerializedName("address")
    private String address;

    @SerializedName("frequency_results_json")
    private String frequencyResultsJson; //HearscreenFrequencyResult[]

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

    public boolean isPendingInTest() {
        return pendingInTest;
    }

    public void setPendingInTest(boolean pendingInTest) {
        this.pendingInTest = pendingInTest;
    }

    public Long getServerDeviceId() {
        return serverDeviceId;
    }

    public void setServerDeviceId(Long serverDeviceId) {
        this.serverDeviceId = serverDeviceId;
    }

    public String getGeneratedFacilityId() {
        return generatedFacilityId;
    }

    public void setGeneratedFacilityId(String generatedFacilityId) {
        this.generatedFacilityId = generatedFacilityId;

        public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

        public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

      public String getFrequencyResultsJson() {
        return frequencyResultsJson;
    }

    public void setFrequencyResultsJson(String frequencyResultsJson) {
        this.frequencyResultsJson = frequencyResultsJson;
    }



    }*/

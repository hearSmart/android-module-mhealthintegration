/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */

package com.hearxgroup.mhealthintegration.Models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.hearxgroup.mhealth.resources.Const;

import java.util.List;

import timber.log.Timber;

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
public class HeartestTest {

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

    @SerializedName("headset_cal_id")
    private Long headsetCalId;

    @SerializedName("device_std_id")
    private Long deviceStdId;

    //LOCATION INFO
    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;

    @SerializedName("known_impairment_type")
    private int knownImpairmentType;

    @SerializedName("pending_upload")
    private Boolean pendingUpload;

    @SerializedName("pending_retrieval")
    private Boolean pendingRetrieval;

    @SerializedName("mhealth_version")
    private int mHealthVersion;

    @SerializedName("created_at")
    private Long createdAt;

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

    @SerializedName("test_type")
    private Const.HEARTEST_TEST_TYPE testType;

    @SerializedName("patient_signature")
    private String patientSignature;

    @SerializedName("license_ids")
    private List<Long> licenseIds;

    //POPULATED FROM HEARTEST APP
    @SerializedName("test_date")
    private Long testDate;
   @SerializedName("operation_mode")
    private int operationMode;
    @SerializedName("protocol_obj")
    private ProtocolHearTest protocol;
    @SerializedName("duration")
    private int duration;
    @SerializedName("software_version")
    private String softwareVersion;
    @SerializedName("notes")
    private String notes;
    @SerializedName("mean_response_time")
    private double meanResponseTime;
    @SerializedName("std_dev_response_time")
    private double stdDevResponseTime;
    @SerializedName("total_responses")
    private int totalResponses;
    @SerializedName("total_false_responses")
    private int totalFalseResponses;
    @SerializedName("pta_left")
    private double ptaLeft;
    @SerializedName("pta_right")
    private double ptaRight;

    @SerializedName("otoscopy_completed")
    private Boolean otoscopyCompleted;

    @SerializedName("otoscopy_uuid")
    private String otoscopyUuid;

    @SerializedName("subjective_test")
    private Boolean subjectTest;

    @SerializedName("subjective_test_response")
    private Boolean subjectTestResponse;

    @SerializedName("retest")
    private Boolean retest;

    @SerializedName("intest_flags")
    private List<Const.INTEST_WARNING_FLAG> intestFlags;

    @SerializedName("stat_ub_mean_response_time")
    private int statUbMeanResponseTime;

    @SerializedName("stat_ub_std_dev_response_time")
    private int statUbStdDevResponseTime;

    @SerializedName("stat_ub_false_response_count")
    private int statUbFalseResponseCount;

    @SerializedName("stat_ub_test_retest")
    private int statUbTestRetest;

    @SerializedName("state")
    private int state;

    @SerializedName("last_upload_attempt")
    private Long lastUploadAttempt;

    @SerializedName("frequency_results")
    private FrequencyResultHearTest[] frequencyResults;

    public static HeartestTest fromJson(String json) {
        Log.d("HeartestTest","JsonData" + json);
        return new Gson().fromJson(json, HeartestTest.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
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

    public List<Long> getLicenseIds() {
        return licenseIds;
    }

    public void setLicenseIds(List<Long> licenseIds) {
        this.licenseIds = licenseIds;
    }

    public Long getTestDate() {
        return testDate;
    }

    public void setTestDate(Long testDate) {
        this.testDate = testDate;
    }

    public int getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(int operationMode) {
        this.operationMode = operationMode;
    }

    public ProtocolHearTest getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolHearTest protocol) {
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

    public double getMeanResponseTime() {
        return meanResponseTime;
    }

    public void setMeanResponseTime(double meanResponseTime) {
        this.meanResponseTime = meanResponseTime;
    }

    public double getStdDevResponseTime() {
        return stdDevResponseTime;
    }

    public void setStdDevResponseTime(double stdDevResponseTime) {
        this.stdDevResponseTime = stdDevResponseTime;
    }

    public int getTotalResponses() {
        return totalResponses;
    }

    public void setTotalResponses(int totalResponses) {
        this.totalResponses = totalResponses;
    }

    public int getTotalFalseResponses() {
        return totalFalseResponses;
    }

    public void setTotalFalseResponses(int totalFalseResponses) {
        this.totalFalseResponses = totalFalseResponses;
    }

    public double getPtaLeft() {
        return ptaLeft;
    }

    public void setPtaLeft(double ptaLeft) {
        this.ptaLeft = ptaLeft;
    }

    public double getPtaRight() {
        return ptaRight;
    }

    public void setPtaRight(double ptaRight) {
        this.ptaRight = ptaRight;
    }

    public FrequencyResultHearTest[] getFrequencyResults() {
        return frequencyResults;
    }

    public void setFrequencyResults(FrequencyResultHearTest[] frequencyResults) {
        this.frequencyResults = frequencyResults;
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

/*    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }*/

    public Const.HEARTEST_TEST_TYPE getTestType() {
        return testType;
    }

    public void setTestType(Const.HEARTEST_TEST_TYPE testType) {
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

    public Boolean getSubjectTest() {
        return subjectTest;
    }

    public void setSubjectTest(Boolean subjectTest) {
        this.subjectTest = subjectTest;
    }

    public Boolean getSubjectTestResponse() {
        return subjectTestResponse;
    }

    public void setSubjectTestResponse(Boolean subjectTestResponse) {
        this.subjectTestResponse = subjectTestResponse;
    }

    public Boolean getRetest() {
        return retest;
    }

    public void setRetest(Boolean retest) {
        this.retest = retest;
    }

    public List<Const.INTEST_WARNING_FLAG> getIntestFlags() {
        return intestFlags;
    }

    public void setIntestFlags(List<Const.INTEST_WARNING_FLAG> intestFlags) {
        this.intestFlags = intestFlags;
    }

    public int getStatUbMeanResponseTime() {
        return statUbMeanResponseTime;
    }

    public void setStatUbMeanResponseTime(int statUbMeanResponseTime) {
        this.statUbMeanResponseTime = statUbMeanResponseTime;
    }

    public int getStatUbStdDevResponseTime() {
        return statUbStdDevResponseTime;
    }

    public void setStatUbStdDevResponseTime(int statUbStdDevResponseTime) {
        this.statUbStdDevResponseTime = statUbStdDevResponseTime;
    }

    public int getStatUbFalseResponseCount() {
        return statUbFalseResponseCount;
    }

    public void setStatUbFalseResponseCount(int statUbFalseResponseCount) {
        this.statUbFalseResponseCount = statUbFalseResponseCount;
    }

    public int getStatUbTestRetest() {
        return statUbTestRetest;
    }

    public void setStatUbTestRetest(int statUbTestRetest) {
        this.statUbTestRetest = statUbTestRetest;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getLastUploadAttempt() {
        return lastUploadAttempt;
    }

    public void setLastUploadAttempt(Long lastUploadAttempt) {
        this.lastUploadAttempt = lastUploadAttempt;
    }
}


/*   @SerializedName("protocol_min_intensity")
    private int protocolMinIntensity;

    @SerializedName("frequency_results_json")
    private String frequencyResultsJson; //HeartestFrequencyResult[]

    @SerializedName("pending_in_test")
    private boolean pendingInTest;

    @SerializedName("address")
    private String address;

    @SerializedName("generated_facility_id")
    private String generatedFacilityId;

    @SerializedName("device_id")
    private Long serverDeviceId;

    @SerializedName("generated_patient_id")
    private String generatedPatientId;

    @SerializedName("generated_id")
     private String generatedId;

    @SerializedName("protocol_id")
    private Long protocolId;


    public String getGeneratedId() {
        return generatedId;
    }

    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }

    /*public String getGeneratedPatientId() {
        return generatedPatientId;
    }

    public void setGeneratedPatientId(String generatedPatientId) {
        this.generatedPatientId = generatedPatientId;
    }


    public int getProtocolMinIntensity() {
        return protocolMinIntensity;
    }

    public void setProtocolMinIntensity(int protocolMinIntensity) {
        this.protocolMinIntensity = protocolMinIntensity;
    }

    public String getFrequencyResultsJson() {
        return frequencyResultsJson;
    }

    public void setFrequencyResultsJson(String frequencyResultsJson) {
        this.frequencyResultsJson = frequencyResultsJson;
    }


    public boolean isPendingInTest() {
        return pendingInTest;
    }

    public void setPendingInTest(boolean pendingInTest) {
        this.pendingInTest = pendingInTest;
    }


   public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    }

 */


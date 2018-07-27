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
    //TODO testType
    /*private int testType; //hearScreen/hearTest/hearDin/hearScope/Peek Acuity.*/
    @NonNull
    private String generatedId; //24 character length randomly generated string *NB REQUIRED
    @NonNull
    private String gender; // male/female *NB REQUIRED
    @NonNull
    private String birthDate; // YYYY-MM-DD *NB REQUIRED
    @NonNull
    private String firstName; //*NB REQUIRED
    @NonNull
    private String lastName; //*NB REQUIRED
    @NonNull
    private String languageIso3; //*NB REQUIRED
    @Nullable
    private String idNumber;
    @Nullable
    private String mrn; //medical record number
    @Nullable
    private String email;
    @Nullable
    private String contactNumber; // with country code prefix. eg +27762223333
    @Nullable
    private String returnIntentActionName; //Return intent filter action name

    public static MHealthTestRequest build(
            String generatedId,
            String gender,
            String birthdate,
            String firstName,
            String lastName,
            String languageIso3,
            String idNumber,
            String mrn,
            String email,
            String contactNumber,
            String returnIntentActionName) {
        MHealthTestRequest mHealthTestRequest = new MHealthTestRequest();
        mHealthTestRequest.setGeneratedId(generatedId);
        mHealthTestRequest.setGender(gender);
        mHealthTestRequest.setBirthDate(birthdate);
        mHealthTestRequest.setFirstName(firstName);
        mHealthTestRequest.setLastName(lastName);
        mHealthTestRequest.setLanguageIso3(languageIso3);
        mHealthTestRequest.setIdNumber(idNumber);
        mHealthTestRequest.setMrn(mrn);
        mHealthTestRequest.setEmail(email);
        mHealthTestRequest.setContactNumber(contactNumber);
        mHealthTestRequest.setReturnIntentActionName(returnIntentActionName);

        return mHealthTestRequest;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGeneratedId() {
        return generatedId;
    }

    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLanguageIso3() {
        return languageIso3;
    }

    public void setLanguageIso3(String languageIso3) {
        this.languageIso3 = languageIso3;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getReturnIntentActionName() {
        return returnIntentActionName;
    }

    public void setReturnIntentActionName(String returnIntentActionName) {
        this.returnIntentActionName = returnIntentActionName;
    }

    public static MHealthTestRequest fromJson(String json) {
        return new Gson().fromJson(json, MHealthTestRequest.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

}

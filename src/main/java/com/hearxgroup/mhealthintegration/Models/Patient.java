/*
 *
 * *
 *  Copyright (c) 2016-2018 hearX Group (Pty) Ltd. All rights reserved
 *  Contact info@hearxgroup.com
 *  Created by David Howe
 *  Last modified David Howe on 2018/03/08 10:30 PM
 * /
 */

package com.hearxgroup.mhealthintegration.Models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Patient {
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("contact_number")
    private String contactNumber;
    @SerializedName("birth_date")
    private String birthDate;
    @SerializedName("gender")
    private String gender;
    @SerializedName("language_code")
    private String languageCode;
    @SerializedName("identification_number")
    private String identificationNumber;
    @SerializedName("reference_number")
    private String referenceNumber;

    public static Patient build(
            @NonNull String firstName,
            @NonNull String lastName,
            @NonNull String birthDate,
            @NonNull String gender,
            @NonNull String languageCode,
            @Nullable String email,
            @Nullable String contactNumber,
            @Nullable String identificationNumber,
            @Nullable String referenceNumber) {

        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setBirthDate(birthDate);
        patient.setGender(gender);
        patient.setLanguageCode(languageCode);
        patient.setEmail(email);
        patient.setContactNumber(contactNumber);
        patient.setIdentificationNumber(identificationNumber);
        patient.setReferenceNumber(referenceNumber);
        return patient;
    }

    public static Patient fromJson(String json) {
        return new Gson().fromJson(json, Patient.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}

/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */

package com.hearxgroup.mhealthintegration.Models;

import android.content.Context;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.regex.Pattern;

import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;

import static com.hearxgroup.mhealth.resources.Const.GENDER.male;
import static com.hearxgroup.mhealth.resources.Const.GENDER.female;
//import static com.hearxgroup.hearx.Constants.TEXT_GENDER_MALE;

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
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

    public String validate(Context context) {
        if (getGender() == null || (!getGender().equalsIgnoreCase(male.name()) && !getGender().equalsIgnoreCase(female.name())))
            return "Invalid gender parameter";
        else if (getFirstName() == null || getFirstName().length() < 1)
            return "Invalid first name parameter";
        else if (getLastName() == null || getLastName().length() < 1)
            return "Invalid last name parameter";
        else if (getLanguageCode() == null || getLanguageCode().length() != 3)
            return "Invalid ISO3 language parameter";
        else if (getEmail() != null && getEmail().length() > 0 && !isValidEmail(getEmail()))
            return "Invalid email parameter";
        else if (getContactNumber() != null && getContactNumber().length() > 0) {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.createInstance(context);
            try {
                if (!phoneUtil.isValidNumber(phoneUtil.parse(getContactNumber(), "")))
                    return "Invalid contact no. parameter";
            } catch (NumberParseException e) {
                return "Invalid contact no. parameter";
            }
        }

        //TEST BIRTHDATE ENTRY
        try {
            String birthdate = getBirthDate();
            int year = Integer.parseInt(birthdate.substring(0, 4));
            int month = Integer.parseInt(birthdate.substring(5, 7));
            int day = Integer.parseInt(birthdate.substring(8, 10));

            LocalDate birthdateDate = new LocalDate(year, month, day);
            LocalDate now = new LocalDate();
            Years age = Years.yearsBetween(birthdateDate, now);
            if (age.getYears() < 3)
                return "Patient too young for test";
        }
        catch (Exception e) {
            return "Invalid birthdate parameter";
        }

        return null;
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}

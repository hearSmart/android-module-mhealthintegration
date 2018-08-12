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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.regex.Pattern;

import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import lombok.Getter;
import lombok.Setter;

import static com.hearxgroup.mhealthintegration.Constants.TEXT_GENDER_FEMALE;
import static com.hearxgroup.mhealthintegration.Constants.TEXT_GENDER_MALE;

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

    public String validate(Context context) {
        if (getGender() == null || (!getGender().equalsIgnoreCase(TEXT_GENDER_MALE) && !getGender().equalsIgnoreCase(TEXT_GENDER_FEMALE)))
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
}

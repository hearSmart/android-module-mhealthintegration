package com.hearxgroup.mhealthintegration;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Patterns;

import com.hearxgroup.mhealthintegration.Models.MHealthTestRequest;
import com.hearxgroup.mhealthintegration.Models.Patient;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.List;
import java.util.regex.Pattern;

import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;

import static com.hearxgroup.mhealthintegration.Constants.TEXT_GENDER_FEMALE;
import static com.hearxgroup.mhealthintegration.Constants.TEXT_GENDER_MALE;

/**
 * Copyright (c) 2017 hearX Group (Pty) Ltd. All rights reserved
 * Created by David Howe on 2018/07/27.
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
public class Util {

    public static String validateTestRequest(Context context, MHealthTestRequest testRequest) {
        if(testRequest==null)
            return "Malformed Request Object";
        else if(testRequest.getGeneratedId()==null || testRequest.getGeneratedId().length()<24)
            return "Invalid generatedId parameter";
        else {
            //TESTS PATIENT VALIDATION
             if(testRequest.getPatientJson()!=null) {
                 String patientValidation = validatePatient(context, Patient.fromJson(testRequest.getPatientJson()));
                 if(patientValidation!=null)
                     return patientValidation;
             }
            //TEST returnIntentActionName PARAM
            String returnIntentActionName = testRequest.getReturnIntentActionName();
            if(returnIntentActionName==null || returnIntentActionName.length()==0)
                return "returnIntentActionName parameter required";

            return null;
        }

    }

    public static String validatePatient(Context context, Patient patient) {
        if (patient.getGender() == null || (!patient.getGender().equalsIgnoreCase(TEXT_GENDER_MALE) && !patient.getGender().equalsIgnoreCase(TEXT_GENDER_FEMALE)))
            return "Invalid gender parameter";
        else if (patient.getFirstName() == null || patient.getFirstName().length() < 1)
            return "Invalid first name parameter";
        else if (patient.getLastName() == null || patient.getLastName().length() < 1)
            return "Invalid last name parameter";
        else if (patient.getLanguageCode() == null || patient.getLanguageCode().length() != 3)
            return "Invalid ISO3 language parameter";
        else if (patient.getEmail() != null && patient.getEmail().length() > 0 && !isValidEmail(patient.getEmail()))
            return "Invalid email parameter";
        else if (patient.getContactNumber() != null && patient.getContactNumber().length() > 0) {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.createInstance(context);
            try {
                if (!phoneUtil.isValidNumber(phoneUtil.parse(patient.getContactNumber(), "")))
                    return "Invalid contact no. parameter";
            } catch (NumberParseException e) {
                return "Invalid contact no. parameter";
            }
        }

        //TEST BIRTHDATE ENTRY
        try {
            String birthdate = patient.getBirthDate();
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

    public static boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static boolean openApp(Context context, Bundle bundle, String actionName) {
        Intent intent = new Intent(actionName);
        intent.setType("text/plain");
        List<ResolveInfo> activities = context.getPackageManager().queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;
        if (isIntentSafe) {
            if(bundle!=null)
                intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
            return true;
        }
        return false; //MHEALTH APP INSTALLATION COULD NOT BE FOUND
    }
}

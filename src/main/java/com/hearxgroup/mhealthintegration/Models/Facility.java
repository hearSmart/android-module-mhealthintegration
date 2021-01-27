/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */

package com.hearxgroup.mhealthintegration.Models;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
public class Facility {
    public static final String TAG = Facility.class.getSimpleName();

    private String name;
    private String email;
    @SerializedName("contact_number")
    private String contactNumber;
    @SerializedName("manager_name")
    private String managerName;
    private String address;
    private double lat;
    private double lng;

    public Facility() {
    }

    public static Facility build(
            @NonNull String name,
            @NonNull String email,
            @NonNull String contactNumber,
            @NonNull String managerName,
            @NonNull String address,
            double lat,
            double lng) {

        Facility facility = new Facility();
        facility.setName(name);
        facility.setEmail(email);
        facility.setContactNumber(contactNumber);
        facility.setManagerName(managerName);
        facility.setAddress(address);
        facility.setLat(lat);
        facility.setLng(lng);
        return facility;
    }

    public static Facility fromJson(String json) {
        return new Gson().fromJson(json, Facility.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}

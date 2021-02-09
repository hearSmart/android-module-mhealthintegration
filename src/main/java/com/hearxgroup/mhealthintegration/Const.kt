package com.hearxgroup.mhealthintegration

import android.os.Environment

object Const {

    val BUNDLE_EXTRA_MHTEST_REQUEST_JSON = "mhealthstudio.BUNDLE_EXTRA_MHTEST_REQUEST_JSON"

    fun getTestRequestPath(): String? {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/mhealth/mhealthtest.txt"
    }
}
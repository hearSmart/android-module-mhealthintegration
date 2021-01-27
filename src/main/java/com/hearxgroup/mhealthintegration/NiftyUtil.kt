/*
 * Copyright © 2018 - 2019 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */
package com.hearxgroup.mhealthintegration

import android.util.Base64
import android.util.Patterns
import io.reactivex.Single
import io.reactivex.SingleEmitter
import java.io.*
import java.math.BigInteger
import java.nio.charset.Charset
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by David Howe
 * hearX Group (Pty) Ltd.
 */
object NiftyUtil {
    fun getMean(data: ArrayList<Int>): Double {
        var sum = 0.0
        for (a in data) sum += a
        return round(sum / data.size, 2)
    }

    fun getVariance(data: ArrayList<Int>): Double {
        val mean = getMean(data)
        var temp = 0.0
        for (a in data) temp += (mean - a) * (mean - a)
        return temp / data.size
    }

    @JvmStatic
    fun getStdDev(data: ArrayList<Int>): Double {
        return round(Math.sqrt(getVariance(data)), 2)
    }

    @JvmStatic
    fun round(value: Double, precision: Int): Double {
        val scale = Math.pow(10.0, precision.toDouble()).toInt()
        return Math.round(value * scale).toDouble() / scale
    }

    fun getRandomValueInclusive(low: Int, high: Int): Int {
        val r = Random()
        return r.nextInt(high + 1 - low) + low
    }

    /**
     * Validates date of format yyyy-MM-dd
     * @param dateToValidate
     * @return
     */
    fun isValidDate(dateToValidate: String?): Boolean {
        val dataFormat = "yyyy-MM-dd"
        if (dateToValidate == null) {
            return false
        }
        val sdf = SimpleDateFormat(dataFormat)
        sdf.isLenient = false
        try {
            //if not valid, it will throw ParseException
            val date = sdf.parse(dateToValidate)
            println(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            return false
        }
        return true
    }

    fun isValidEmail(email: String?): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    /*public static String getFormattedContactNumber(Context context, final String countryCodeWithPlus, final String contactNo) {
        if(countryCodeWithPlus!=null && contactNo!=null && contactNo.length()>0) {
            String cachedContactNo;
            try {
                PhoneNumberUtil phoneUtil = PhoneNumberUtil.createInstance(context);
                Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(countryCodeWithPlus + contactNo, "");
                cachedContactNo = countryCodeWithPlus+phoneNumber.getNationalNumber();
            }
            catch (NumberParseException e) {
                System.err.println("NumberParseException was thrown: " + e.toString());
                cachedContactNo = countryCodeWithPlus + contactNo;
            }
            return cachedContactNo;
        }
        else
            return null;
    }*/
    fun encodeToBase64String(data: String): String {
        return Base64.encodeToString(data.toByteArray(), 0)
    }

    fun decodeBase64String(base64String: String?): Single<String?> {
        return Single.create { emitter: SingleEmitter<String?> ->
            try {
                val data = Base64.decode(base64String, 0)
                val decodedString = String(data, Charset.forName("UTF-8"))
                emitter.onSuccess(decodedString)
            } catch (var4: IOException) {
                emitter.onError(Throwable(var4.message))
            }
        }
    }

    val randomSequence: String
        get() = UUID.randomUUID().toString().replace("-".toRegex(), "")

    /**
     * Calculates the MD5 hash given a file
     * @param updateFile
     * @return
     */
    fun calculateMD5(updateFile: File?): String? {
        val digest: MessageDigest
        digest = try {
            MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            return null
        }
        val `is`: InputStream
        `is` = try {
            FileInputStream(updateFile)
        } catch (e: FileNotFoundException) {
            return null
        }
        val buffer = ByteArray(8192)
        var read: Int
        return try {
            while (`is`.read(buffer).also { read = it } > 0) {
                digest.update(buffer, 0, read)
            }
            val md5sum = digest.digest()
            val bigInt = BigInteger(1, md5sum)
            var output = bigInt.toString(16)
            // Fill to 32 chars
            output = String.format("%32s", output).replace(' ', '0')
            output
        } catch (e: IOException) {
            throw RuntimeException("Unable to process file for MD5", e)
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
            }
        }
    }

    fun getMD5(data: String): String {
        return try {
            val digest = MessageDigest.getInstance("md5")
            digest.update(data.toByteArray())
            val bytes = digest.digest()
            val sb = StringBuilder()
            for (i in bytes.indices) {
                sb.append(String.format("%02X", bytes[i]))
            }
            sb.toString().toLowerCase()
        } catch (var6: Exception) {
            ""
        }
    }
}
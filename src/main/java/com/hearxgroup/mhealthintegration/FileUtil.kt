/*
 * Copyright © 2018 - 2020 hearX IP (Pty) Ltd.
 * Copyright subsists in this work and it is copyright protected under the Berne Convention.  No part of this work may be reproduced, published, performed, broadcasted, adapted or transmitted in any form or by any means, electronic or mechanical, including photocopying, recording or by any information storage and retrieval system, without permission in writing from the copyright owner
 * hearX Group (Pty) Ltd.
 * info@hearxgroup.com
 */

package com.hearxgroup.mhealthintegration

import io.reactivex.Single
import timber.log.Timber
import java.io.*
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class FileUtil {
    companion object {
        fun writeFile(fileContents: String, filePath: String): Single<Optional<Boolean>> {
            try {
                val toFile = File(filePath)
                toFile.mkdirs()
                if (toFile.exists()) {
                    toFile.delete()
                }

                if (toFile.createNewFile()) {
                    val fos = FileOutputStream(toFile)
                    fos.write(fileContents.toByteArray())
                    fos.close()
                    return Single.fromCallable<Optional<Boolean>> { Optional.of(true) }
                } else {
                    return Single.fromCallable<Optional<Boolean>> { Optional.of(false) }
                }
            } catch (var4: IOException) {
                return Single.fromCallable<Optional<Boolean>> { Optional.of(false) }
            }

        }

        fun readFile(path: String): Single<String> {
            Timber.d("readFile path=$path")
            return Single.create { emitter ->
                Timber.d("Single.create")
                var fileString = ""
                val readFile = File(path)
                Timber.d("readFile prog 1")
                val fileInputStream = FileInputStream(readFile)
                Timber.d("readFile prog 2")
                val myReader = BufferedReader(InputStreamReader(fileInputStream))
                Timber.d("readFile prog 3")
                var aDataRow : String?
                aDataRow = myReader.readLine()
                while (aDataRow != null) {
                    fileString += aDataRow
                    aDataRow = myReader.readLine()
                }

                Timber.d("fileString=$path")

                myReader.close()
                fileInputStream.close()
                emitter.onSuccess(fileString)
            }
        }

        fun getFileSizeKB(path: String): Long {
            return File(path).length()/1024
        }

        fun deleteFile(path: String): Single<Boolean> {
            return Single.create { emitter ->
                val file = File(path)
                emitter.onSuccess(file.delete())
            }
        }

        fun calculateMD5(updateFile: File): String? {
            val digest: MessageDigest
            try {
                digest = MessageDigest.getInstance("MD5")
            } catch (var20: NoSuchAlgorithmException) {
                return null
            }

            val fis: FileInputStream
            try {
                fis = FileInputStream(updateFile)
            } catch (var19: FileNotFoundException) {
                return null
            }

            val buffer = ByteArray(8192)

            val var8: String
            try {
                var read = 0

                read  = fis.read(buffer)
                while (read > 0) {
                    digest.update(buffer, 0, read)
                    read  = fis.read(buffer)
                }

                val md5sum = digest.digest()
                val bigInt = BigInteger(1, md5sum)
                var output = bigInt.toString(16)
                output = String.format("%32s", output).replace(' ', '0')
                var8 = output
            } catch (var21: IOException) {
                throw RuntimeException("Unable to process file for MD5", var21)
            } finally {
                try {
                    fis.close()
                } catch (var18: IOException) {
                }

            }

            return var8
        }

        fun doesFileExist(filePath: String) : Boolean {
            val file = File(filePath)
            return file.exists()
        }

    }
}
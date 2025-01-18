package com.metamamun.equipment.utils

import android.content.Context
import android.content.pm.PackageManager

import com.metamamun.equipment.enums.VerifyInput


private const val TITLE_PATTERN = "^[\\w\\d_.-]+$"
private const val EMAIL_PATTERN = "^[a-zA-Z0-9._-]{1,256}+@[a-zA-Z0-9][a-zA-Z0-9-]{0,64}+\\.[a-zA-Z0-9][a-zA-Z0-9-]{0,25}+(?:\\.[a-zA-Z]{1,4})?$"
private const val ADDRESS_PATTERN = ""
private const val DESCRIPTION_PATTERN = ""
private const val PHONE_PATTERN = "^(?:\\+8801|8801|01)(\\d{9})$"

fun String.isValid(type: VerifyInput): Boolean{
    return when(type){
        VerifyInput.TITLE -> this.isNotBlank() and TITLE_PATTERN.toRegex().matches(this)
        VerifyInput.EMAIL -> this.isNotBlank() and EMAIL_PATTERN.toRegex().matches(this)
        VerifyInput.ADDRESS -> this.isNotBlank() and ADDRESS_PATTERN.toRegex().matches(this)
        VerifyInput.DESCRIPTION -> this.isNotBlank() and DESCRIPTION_PATTERN.toRegex().matches(this)
        VerifyInput.PHONE -> this.isNotBlank() and PHONE_PATTERN.toRegex().matches(this)
    }
}

fun Context.getVersionNameAsString(): String {
    return try {
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        packageInfo.versionName ?: "Unknown"
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        "Unknown"
    }
}
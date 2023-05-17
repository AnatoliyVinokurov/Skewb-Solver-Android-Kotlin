package com.anatoliyvinokurov.skewbsolver

import android.os.Bundle
import android.content.Context
import android.content.pm.PackageManager
import android.Manifest

object AppModificationChecker {
    fun hasInternetPermission(context: Context): Boolean {
        val permission = Manifest.permission.INTERNET
        val granted = PackageManager.PERMISSION_GRANTED

        return context.checkSelfPermission(permission) == granted
    }
}

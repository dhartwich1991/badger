package com.dhartwich.badges.helpers

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager


class HomePackageFinder {
    fun findHomePackage(context: Context): String {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)

        val resolveInfo = context.packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        if (resolveInfo != null && resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName != null) {
            return resolveInfo.activityInfo.packageName
        }

        return context.packageName
    }
}
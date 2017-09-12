package com.dhartwich.badges.providers

import android.content.ContentValues
import android.content.Context
import android.net.Uri

private const val COLUMN_ID = "_id"
private const val COLUMN_PACKAGE = "package"
private const val COLUMN_CLASS = "class"
private const val COLUMN_BADGE_COUNT = "badgeCount"

internal class SamsungBadgeProvider(private val context: Context) : BadgeProvider {
    companion object {
        private val CONTENT_URI = Uri.parse("content://com.sec.badge/apps")
        const val HOME_PACKAGE = "com.sec.android.app.launcher"
    }

    override fun showBadge(count: Int) {
        try {
            val contentResolver = context.contentResolver
            val cursor = contentResolver.query(CONTENT_URI, arrayOf(COLUMN_ID), COLUMN_PACKAGE + "=?",
                    arrayOf(packageName()), null)

            if (cursor == null || !cursor.moveToFirst()) {
                val contentValues = ContentValues().apply {
                    put(COLUMN_PACKAGE, packageName())
                    put(COLUMN_CLASS, mainActivityClassName())
                    put(COLUMN_BADGE_COUNT, count)
                }
                contentResolver.insert(CONTENT_URI, contentValues)
            } else {
                val idColumnIndex = cursor.getColumnIndex(COLUMN_ID)

                val contentValues = ContentValues()
                contentValues.put(COLUMN_BADGE_COUNT, count)
                contentResolver.update(CONTENT_URI, contentValues, COLUMN_ID + "=?", arrayOf(cursor.getInt
                (idColumnIndex).toString()))
                cursor.close()
            }
        } catch (e: Exception) {
            // Some Samsung devices are throwing SecurityException or RuntimeException when
            // trying to set the badge saying the app needs permission which are already added,
            // this try/catch protect us from these "crappy phones" :)
            throw UnsupportedOperationException()
        } finally {

        }

    }

    override fun removeBadges() {
        showBadge(0)
    }

    override fun packageName(): String = context.packageName

    override fun mainActivityClassName(): String =
            context.packageManager.getLaunchIntentForPackage(packageName()).component.className
}
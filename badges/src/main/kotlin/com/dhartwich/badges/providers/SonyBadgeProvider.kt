package com.dhartwich.badges.providers

import android.content.Context
import android.content.Intent

private const val ACTION = "com.sonyericsson.home.action.UPDATE_BADGE"
private const val PACKAGE_NAME = "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME"
private const val ACTIVITY_NAME = "com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME"
private const val SHOW_MESSAGE = "com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE"
private const val COUNT = "com.sonyericsson.home.intent.extra.badge.MESSAGE"

internal class SonyBadgeProvider(private val context: Context) : BadgeProvider {
    companion object {
        const val HOME_PACKAGE = "com.sonyericsson.home"
    }

    override fun showBadge(count: Int) {
        val intent = Intent(ACTION).apply {
            putExtra(PACKAGE_NAME, packageName())
            putExtra(ACTIVITY_NAME, mainActivityClassName())
            putExtra(SHOW_MESSAGE, count > 0)
            putExtra(COUNT, count.toString())
        }

        context.sendBroadcast(intent)
    }

    override fun removeBadges() {
        showBadge(0)
    }

    override fun packageName(): String = context.packageName

    override fun mainActivityClassName(): String =
            context.packageManager.getLaunchIntentForPackage(packageName()).component.className
}
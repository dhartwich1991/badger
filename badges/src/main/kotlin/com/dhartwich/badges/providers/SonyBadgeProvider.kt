package com.dhartwich.badges.providers

import android.content.Context
import android.content.Intent


internal class SonyBadgeProvider(private val context: Context) : BadgeProvider {
    companion object {
        const val HOME_PACKAGE = "com.sonyericsson.home"
    }

    override fun showBadge(count: Int) {
        val intent = Intent()

        intent.action = "com.sonyericsson.home.action.UPDATE_BADGE"
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", packageName())
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", mainActivityClassName())
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", count > 0)
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", count.toString())

        context.sendBroadcast(intent)
    }

    override fun removeBadges() {
        showBadge(0)
    }

    override fun packageName(): String = context.packageName

    override fun mainActivityClassName(): String =
            context.packageManager.getLaunchIntentForPackage(packageName()).component.className
}
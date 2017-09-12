package com.dhartwich.badges.providers

import android.content.Context
import android.content.Intent


internal class LgBadgeProvider(private val context: Context) : BadgeProvider {
    companion object {
        const val HOME_PACKAGE = "com.lge.launcher2"
    }

    override fun showBadge(count: Int) {
        val intent = Intent("android.intent.action.BADGE_COUNT_UPDATE")
        intent.putExtra("badge_count_package_name", packageName())
        intent.putExtra("badge_count_class_name", mainActivityClassName())
        intent.putExtra("badge_count", count)

        context.sendBroadcast(intent)
    }

    override fun removeBadges() {
        showBadge(0)
    }

    override fun packageName(): String = context.packageName

    override fun mainActivityClassName(): String =
            context.packageManager.getLaunchIntentForPackage(packageName()).component.className
}
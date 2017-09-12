package com.dhartwich.badges.providers

import android.content.Context
import android.content.Intent

private const val ACTION = "android.intent.action.BADGE_COUNT_UPDATE"
private const val BADGE_PACKAGE_NAME = "badge_count_package_name"
private const val BADGE_CLASS_NAME = "badge_count_class_name"
private const val BADGE_COUNT = "badge_count"

internal class LgBadgeProvider(private val context: Context) : BadgeProvider {
    companion object {
        const val HOME_PACKAGE = "com.lge.launcher2"
    }

    override fun showBadge(count: Int) {
        val intent = Intent(ACTION).apply {
            putExtra(BADGE_PACKAGE_NAME, packageName())
            putExtra(BADGE_CLASS_NAME, mainActivityClassName())
            putExtra(BADGE_COUNT, count)
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

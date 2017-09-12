package com.dhartwich.badges.providers

import android.content.ComponentName
import android.content.Context
import android.content.Intent

private const val UPDATE_NOTIFICATION_ACTION = "com.htc.launcher.action.UPDATE_SHORTCUT"
private const val SET_NOTIFICATION_ACTION = "com.htc.launcher.action.SET_NOTIFICATION"
private const val BADGE_PACKAGE_NAME = "packagename"
private const val BADGE_COUNT = "count"
private const val HTC_COMPONENT = "com.htc.launcher.extra.COMPONENT"
private const val HTC_COUNT = "com.htc.launcher.extra.COUNT"

internal class HtcBadgeProvider(private val context: Context) : BadgeProvider {
    companion object {
        const val HOME_PACKAGE = "com.htc.launcher"
    }

    override fun showBadge(count: Int) {
        val intent = Intent(UPDATE_NOTIFICATION_ACTION).apply {
            putExtra(BADGE_PACKAGE_NAME, packageName())
            putExtra(BADGE_COUNT, count)
        }

        context.sendBroadcast(intent)

        val setNotificationIntent = Intent(SET_NOTIFICATION_ACTION)
        val componentName = ComponentName(packageName(), mainActivityClassName())
        setNotificationIntent.putExtra(HTC_COMPONENT, componentName.flattenToShortString());
        setNotificationIntent.putExtra(HTC_COUNT, count)
        context.sendBroadcast(setNotificationIntent)
    }

    override fun removeBadges() {
        showBadge(0)
    }

    override fun packageName(): String = context.packageName

    override fun mainActivityClassName(): String =
            context.packageManager.getLaunchIntentForPackage(packageName()).component.className
}
package com.dhartwich.badges.providers

import android.content.ComponentName
import android.content.Context
import android.content.Intent

internal class HtcBadgeProvider(private val context: Context) : BadgeProvider {
    companion object {
        const val HOME_PACKAGE = "com.htc.launcher"
    }
    override fun showBadge(count: Int) {
        val intent = Intent("com.htc.launcher.action.UPDATE_SHORTCUT")
        intent.putExtra("packagename", packageName())
        intent.putExtra("count", count)
        context.sendBroadcast(intent)


        val setNotificationIntent = Intent("com.htc.launcher.action.SET_NOTIFICATION")
        val componentName = ComponentName(packageName(), mainActivityClassName())
        setNotificationIntent.putExtra("com.htc.launcher.extra.COMPONENT", componentName.flattenToShortString());
        setNotificationIntent.putExtra("com.htc.launcher.extra.COUNT", count);
        context.sendBroadcast(setNotificationIntent);
    }

    override fun removeBadges() {
        showBadge(0)
    }

    override fun packageName(): String = context.packageName

    override fun mainActivityClassName(): String =
            context.packageManager.getLaunchIntentForPackage(packageName()).component.className
}
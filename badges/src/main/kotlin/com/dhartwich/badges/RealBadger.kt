package com.dhartwich.badges

import android.content.Context
import android.util.Log
import com.dhartwich.badges.providers.BadgeProviderFactory

internal class RealBadger(context: Context) : Badger() {
    private val badgeProviderFactory: BadgeProviderFactory = BadgeProviderFactory(context)

    override fun appBadgeCount(count: Int) {
        val badgeProvider = badgeProviderFactory.getBadgeProvider()
        Log.d("Badger", "Using the following badgeprovider = " + badgeProvider)
        badgeProvider?.showBadge(count)
    }

    override fun removeBadges() {
        appBadgeCount(0)
    }
}
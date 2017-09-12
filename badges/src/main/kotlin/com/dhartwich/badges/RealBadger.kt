package com.dhartwich.badges

import android.content.Context
import android.util.Log
import com.dhartwich.badges.providers.BadgeProviderFactory

internal class RealBadger(context: Context) : Badger() {
    private val badgeProviderFactory: BadgeProviderFactory = BadgeProviderFactory(context).also {
        Log.d("Badger", "Using the following badgeprovider = " + it)
    }

    override fun appBadgeCount(count: Int) {
        badgeProviderFactory.provider.showBadge(count)
    }

    override fun removeBadges() {
        appBadgeCount(0)
    }
}
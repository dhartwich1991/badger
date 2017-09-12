package com.dhartwich.badges.providers

import android.content.Context
import com.dhartwich.badges.helpers.HomePackageFinder

internal class BadgeProviderFactory(private val context: Context) {
    val provider: BadgeProvider = initializeProvider(homePackage())

    private fun initializeProvider(launcherName: String): BadgeProvider {
        return when (launcherName) {
            SamsungBadgeProvider.HOME_PACKAGE -> SamsungBadgeProvider(context)
            SonyBadgeProvider.HOME_PACKAGE -> SonyBadgeProvider(context)
            HtcBadgeProvider.HOME_PACKAGE -> HtcBadgeProvider(context)
            LgBadgeProvider.HOME_PACKAGE -> LgBadgeProvider(context)
            else -> DefaultBadgeProvider()
        }
    }

    private fun homePackage(): String = HomePackageFinder().findHomePackage(context)
}

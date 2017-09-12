package com.dhartwich.badges.providers

import android.content.Context
import com.dhartwich.badges.helpers.HomePackageFinder

internal class BadgeProviderFactory(private val context: Context) {
    private var providers: HashMap<String, BadgeProvider> = HashMap()

    init {
        providers.apply {
            put(SamsungBadgeProvider.HOME_PACKAGE, SamsungBadgeProvider(context))
            put(HtcBadgeProvider.HOME_PACKAGE, HtcBadgeProvider(context))
            put(LgBadgeProvider.HOME_PACKAGE, LgBadgeProvider(context))
            put(SonyBadgeProvider.HOME_PACKAGE, SonyBadgeProvider(context))
        }
    }

    fun getBadgeProvider(): BadgeProvider? {
        val currentPackage = homePackage(context)

        if (providers.containsKey(currentPackage)) {
            return providers[currentPackage]
        }

        return EmptyBadgeProvider()
    }

    private fun homePackage(context: Context): String = HomePackageFinder().findHomePackage(context)
}

class EmptyBadgeProvider : BadgeProvider {
    override fun showBadge(count: Int) {
    }

    override fun removeBadges() {
    }

    override fun packageName(): String = ""

    override fun mainActivityClassName(): String = ""

}

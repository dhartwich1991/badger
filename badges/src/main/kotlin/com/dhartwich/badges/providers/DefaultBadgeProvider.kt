package com.dhartwich.badges.providers

/**
 * Default Implementation of [BadgeProvider]. Doesn't actually set any badges. This will be called on non-supported devices.
 */
class DefaultBadgeProvider : BadgeProvider {
    override fun showBadge(count: Int) {}

    override fun removeBadges() {}


    override fun packageName(): String = ""

    override fun mainActivityClassName(): String = ""
}
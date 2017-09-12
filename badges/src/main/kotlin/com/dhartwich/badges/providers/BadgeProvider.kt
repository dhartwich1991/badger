package com.dhartwich.badges.providers

interface BadgeProvider {
    fun showBadge(count: Int)

    fun removeBadges()

    fun packageName(): String

    fun mainActivityClassName(): String
}
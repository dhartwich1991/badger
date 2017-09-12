package com.dhartwich.badges

import android.content.Context

abstract class Badger {

    /** Creates an instance of [Badger]. */
    companion object {
        fun create(context: Context): Badger {
            val realBadger = RealBadger(context)
            return realBadger
        }
    }

    abstract fun appBadgeCount(count: Int)

    abstract fun removeBadges()
}
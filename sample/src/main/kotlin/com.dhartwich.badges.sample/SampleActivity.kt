package com.dhartwich.badges.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dhartwich.badges.Badger
import com.dhartwich.badges.R
import kotlinx.android.synthetic.main.activity_sample.deleteBadges
import kotlinx.android.synthetic.main.activity_sample.showBadges
import kotlinx.android.synthetic.main.activity_sample.toolbar

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        setSupportActionBar(toolbar)
        val badger = Badger.create(this)

        showBadges.setOnClickListener {
            badger.appBadgeCount(5)
        }

        deleteBadges.setOnClickListener {
            badger.removeBadges()
        }
    }

}

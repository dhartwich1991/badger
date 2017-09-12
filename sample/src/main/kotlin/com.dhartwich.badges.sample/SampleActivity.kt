package com.dhartwich.badges.sample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.dhartwich.badges.Badger
import com.dhartwich.badges.R
import kotlinx.android.synthetic.main.activity_sample.deleteBadges
import kotlinx.android.synthetic.main.activity_sample.fab
import kotlinx.android.synthetic.main.activity_sample.showBadges
import kotlinx.android.synthetic.main.activity_sample.toolbar

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        setSupportActionBar(toolbar)
        val badger = Badger.create(this)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        showBadges.setOnClickListener {
            badger.appBadgeCount(5)
        }

        deleteBadges.setOnClickListener {
            badger.removeBadges()
        }
    }

}

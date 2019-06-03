package com.example.amaze.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.View
import com.example.amaze.R
import com.example.amaze.adapters.FirstStepsPagerAdapter
import kotlinx.android.synthetic.main.activity_firststeps.*

class FirstStepsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firststeps)

        val adapter = FirstStepsPagerAdapter(supportFragmentManager)

        welcome_viewPager.adapter = adapter

        welcome_tabLayout.setupWithViewPager(welcome_viewPager)

        welcome_tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                welcome_viewPager.currentItem = tab.position
                if (tab.position == 2) {
                    welcome_tabLayout.visibility = View.INVISIBLE
                    welcome_nextButton.visibility = View.INVISIBLE
                } else {
                    welcome_tabLayout.visibility = View.VISIBLE
                    welcome_nextButton.visibility = View.VISIBLE
                    tab.setIcon(R.drawable.selected_tab)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.setIcon(R.drawable.unselected_tab)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        welcome_nextButton.setOnClickListener({nextTab()})

    }

    private fun nextTab() {
        welcome_viewPager.currentItem = welcome_tabLayout.selectedTabPosition+1
    }
}

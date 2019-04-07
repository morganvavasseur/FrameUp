package com.example.amaze.activities

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import com.example.amaze.R
import com.example.amaze.adapters.LoginSignUpPageAdapater

class LoginSignUpActivity : FragmentActivity() {

    private lateinit var mPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_sign_up)

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.loginSignUpViewPager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = LoginSignUpPageAdapater(supportFragmentManager)
        mPager.adapter = pagerAdapter

    }
}

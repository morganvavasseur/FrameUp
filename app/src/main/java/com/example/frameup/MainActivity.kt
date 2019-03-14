package com.example.amaze

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.example.frameup.activities.AccountFragment
import com.example.frameup.activities.HomeFragment
import com.example.frameup.activities.NotificationsFragment

class MainActivity : AppCompatActivity() {

    lateinit var mMainNav : BottomNavigationView
    lateinit var mMainFrame : FrameLayout

    lateinit var homeFragment: HomeFragment
    lateinit var notificationFragment: NotificationsFragment
    lateinit var accountFragment: AccountFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialise la bar de nav et la fragment view
        this.mMainFrame = findViewById(R.id.main_frame)
        this.mMainNav = findViewById(R.id.main_nav)

        // Initialise un les fragments
        homeFragment = HomeFragment()
        notificationFragment = NotificationsFragment()
        accountFragment = AccountFragment()

        // Affecte le fragment principale à la fragment view
        setFragment(homeFragment)

        this.mMainNav.setOnNavigationItemSelectedListener {
            if (R.id.nav_home == it.itemId) {
                mMainNav.setBackgroundResource(R.color.colorPrimary)
                setFragment(homeFragment)
                true

            }

            else if (R.id.nav_notifications == it.itemId) {
                mMainNav.setBackgroundResource(R.color.colorAccent)
                setFragment(notificationFragment)
                true

            }

            else if (R.id.nav_account == it.itemId) {
                mMainNav.setBackgroundResource(R.color.colorPrimaryDark)
                setFragment(accountFragment)
                true
            }

            else {
                System.out.println("ÇA MARCHE PAS")
                false
            }

        }

        //EventService.getEvents { results, error ->

       // }

        //val intent = Intent(this, LoginActivity::class.java)
        //startActivity(intent)G
    }

    private fun setFragment(fragment: Fragment) {
        var fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()
    }
}

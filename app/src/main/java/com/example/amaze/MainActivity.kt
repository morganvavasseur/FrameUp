package com.example.amaze

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import com.example.amaze.activities.AccountFragment
import com.example.amaze.activities.HomeFragment
import com.example.amaze.activities.NotificationsFragment


class MainActivity : AppCompatActivity() {

    lateinit var mMainNav : BottomNavigationView
    lateinit var mMainFrame : FrameLayout
//    lateinit var mToolbar : Toolbar

    lateinit var homeFragment: HomeFragment
    lateinit var notificationFragment: NotificationsFragment
    lateinit var accountFragment: AccountFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialise la bar de nav et la fragment view
        this.mMainFrame = findViewById(R.id.main_frame)
        this.mMainNav = findViewById(R.id.main_nav)

        // Initialise la toolbar
//        this.mToolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(mToolbar)

        // Initialise un les fragments
        homeFragment = HomeFragment()
        notificationFragment = NotificationsFragment()
        accountFragment = AccountFragment()

        // Affecte le fragment principale à la fragment view
        setFragment(homeFragment)

        this.mMainNav.setOnNavigationItemSelectedListener {
//            mToolbar.menu.clear()
            if (R.id.nav_home == it.itemId) {
                mMainNav.setBackgroundResource(R.color.colorPrimary)
                setFragment(homeFragment)
//                menuInflater.inflate(R.menu.home_toolbar, mToolbar.menu)


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

        //}


    }

    /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_toolbar, menu)
        return true
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()


        if (id == R.id.add_event_button) {
            Toast.makeText(this@MainActivity, "Action clicked", Toast.LENGTH_LONG).show()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setFragment(fragment: Fragment) {
        var fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()
    }

}

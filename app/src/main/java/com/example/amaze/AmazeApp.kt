package com.example.amaze

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.chamber.java.library.SharedChamber

class AmazeApp : Application(){
    // Element principal lancé lorsque l'utilisateur
// lance l'application

    companion object {

        // Création d'un singleton
        lateinit var sharedInstance: AmazeApp
    }

    override fun onCreate() {
        super.onCreate()

        sharedInstance = this
        SharedChamber.initChamber(this)

        //Lock Portrait Mode
        registerActivityLifecycleCallbacks(object  : ActivityLifecycleCallbacks{

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
            }

            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }
        })
    }

}
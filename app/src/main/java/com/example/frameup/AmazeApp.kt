package com.example.frameup

import android.app.Application
import android.content.Intent
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.chamber.java.library.SharedChamber
import com.example.frameup.activities.LoginActivity

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

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

    }

}
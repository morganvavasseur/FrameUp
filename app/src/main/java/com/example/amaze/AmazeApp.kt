package com.example.amaze

import android.app.Application
import android.content.Intent
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

    }

}
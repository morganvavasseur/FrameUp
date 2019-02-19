package com.example.frameup

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class AmazeApp : Application(){
    // Element principal lancé lorsque l'utilisateur
// lance l'application

    // File d'attente pour ordonnancer et exécuter
    // les requêtes réseaux
    lateinit var requestQueue: RequestQueue

    companion object {

        // Création d'un singleton
        lateinit var sharedInstance: AmazeApp
    }

    override fun onCreate() {
        super.onCreate()

        sharedInstance = this
        requestQueue = Volley.newRequestQueue(this)

    }

}
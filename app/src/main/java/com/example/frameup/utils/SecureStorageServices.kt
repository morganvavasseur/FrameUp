package com.example.frameup.utils

import com.chamber.java.library.SharedChamber
import com.chamber.java.library.model.ChamberType
import com.example.frameup.AmazeApp

class SecureStorageServices {

    companion object {
        private var sharedChamber : SharedChamber<*> = SharedChamber
            .ChamberBuilder(AmazeApp.sharedInstance)
            .setChamberType(ChamberType.KEY_256)
            .enableCrypto(true, true)
            .enableKeyPrefix(true, "SECRET_")
            .setPassword("CLEAR_PASSWORD")
            .setFolderName("secure")
            .buildChamber()

        private val AUTH_JWT_TOKEN_KEY = "TOKEN_KEY"

        var authJwtToken : String?
        get() = sharedChamber.getString(AUTH_JWT_TOKEN_KEY, null)
        set(value) {
            sharedChamber.put(AUTH_JWT_TOKEN_KEY, value)
        }
    }


}
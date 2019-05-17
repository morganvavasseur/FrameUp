package com.example.amaze.models

import android.provider.ContactsContract
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.util.*
import java.util.jar.Attributes
import kotlin.collections.ArrayList

@JsonIgnoreProperties(ignoreUnknown = true)
class User : Serializable {

    //
    lateinit var id: String

    // Prénom de l'utilisateur
    lateinit var firstName: String

    // Nom de l'utilisateur
    lateinit var lastName: String

    // Date d'anniversaire de l'utilisateur
    lateinit var birthday: String

    // Numéro de téléphone de l'utilisateur
    lateinit var phone: String

    lateinit var username: String

    lateinit var email: String

    lateinit var diets: ArrayList<Diet>

    lateinit var dietOther: String

    lateinit var invitedEvents: ArrayList<Event>

    lateinit var organizedEvents: ArrayList<Event>

    fun fullName() : String {
        var fullName : String = ""
        if (this.firstName != null) {
            fullName += this.firstName
            if (this.lastName != null)
                fullName += " " + this.lastName
        }
        else
            fullName = "NAME IS NULL"

        return fullName

    }
}
package com.example.amaze.models

import android.provider.ContactsContract
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import java.util.jar.Attributes
import kotlin.collections.ArrayList

@JsonIgnoreProperties(ignoreUnknown = true)
class User {

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

    lateinit var invitedEvents: ArrayList<Event>

    lateinit var organizedEvents: ArrayList<Event>
}
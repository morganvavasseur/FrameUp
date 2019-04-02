package com.example.frameup.models

import android.provider.ContactsContract
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import java.util.jar.Attributes

@JsonIgnoreProperties(ignoreUnknown = true)
class User {
    constructor(id: String, firstName: String, lastName: String){
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.birthday = Date(1998, 7, 10)
        this.phone = "097544678902"
    }

    @JsonProperty("_id")
    lateinit var id: String

    // Prénom de l'utilisateur
    lateinit var firstName: String

    // Nom de l'utilisateur
    lateinit var lastName: String

    // Date d'anniversaire de l'utilisateur
    lateinit var birthday: Date

    // Numéro de téléphone de l'utilisateur
    lateinit var phone: String

    // Stuff sur lesquels l'utilisateur est présent dans les différents event
    lateinit var stuffs: Array<String>

    // Consumables sur lesquels l'utilisateur est présent dans les différents event
    var consumables: Array<String>? = null
}

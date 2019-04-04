package com.example.frameup.models

import android.provider.ContactsContract
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import java.util.jar.Attributes

@JsonIgnoreProperties(ignoreUnknown = true)
class User (
    @JsonProperty("_id")
    val id: String,

    // Prénom de l'utilisateur
    val firstName: String,

    // Nom de l'utilisateur
    val lastName: String,

    // Date d'anniversaire de l'utilisateur
    val birthday: Date,

    // Numéro de téléphone de l'utilisateur
    val phone: String,

    val username: String,

    val email: String
)
package com.example.amaze.network

import com.example.amaze.models.Diet
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import kotlin.collections.ArrayList

class UserResult {
    @JsonProperty("_id")
    lateinit var id: String

    // Prénom de l'utilisateur
    lateinit var firstName: String

    // Nom de l'utilisateur
    lateinit var lastName: String

    // Date d'anniversaire de l'utilisateur
    lateinit var birthday: Date

    lateinit var dietOther: String

    // Numéro de téléphone de l'utilisateur
    lateinit var phone: String

    lateinit var username: String

    lateinit var email: String

    lateinit var diets: ArrayList<Diet>

    lateinit var organizedEvents : ArrayList<EventResult>

    lateinit var invitedEvents : ArrayList<EventResult>
}
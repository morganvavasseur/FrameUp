package com.example.amaze.network

import com.example.amaze.models.*
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

@JsonIgnoreProperties(ignoreUnknown = true)
class UserResult : Person(), Serializable {

    @SerializedName("provider")
    lateinit var provider: String
    @SerializedName("role")
    lateinit var role: Role
    @SerializedName("profilePicture")
    lateinit var profilePicture: String
    @SerializedName("diets")
    lateinit var diets: List<Diet>
    @SerializedName("stuffs")
    lateinit var stuffs : List<Stuff>
    @SerializedName("consumables")
    lateinit var consumables: List<Consumable>
    @SerializedName("organizedEvents")
    lateinit var organizedEvents: List<LinkedEvent>
    @SerializedName("invitedEvents")
    lateinit var invitedEvents: List<LinkedEvent>
    @SerializedName("eventsGoing")
    lateinit var eventsGoing: List<LinkedEvent>
    @SerializedName("eventsNotGoing")
    lateinit var eventsNotGoing: List<LinkedEvent>
    @SerializedName("eventsMaybe")
    lateinit var eventsMaybe: List<LinkedEvent>

}
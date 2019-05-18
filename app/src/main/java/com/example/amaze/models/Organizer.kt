package com.example.amaze.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
class Organizer : Person() {


    @SerializedName("stuffs")
    lateinit var stuffs: List<String>

    @SerializedName("provider")
    lateinit var provider: String
    @SerializedName("role")
    lateinit var role: String
    @SerializedName("organizedEvents")
    lateinit var organizedEvents: List<String>
    @SerializedName("invitedEvents")
    lateinit var invitedEvents: List<String>
}
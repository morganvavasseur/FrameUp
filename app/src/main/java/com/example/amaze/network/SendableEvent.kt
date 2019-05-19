package com.example.amaze.network

import com.example.amaze.models.Consumable
import com.example.amaze.models.Guest
import com.example.amaze.models.Organizer
import com.example.amaze.models.Stuff
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SendableEvent : Serializable {
    @SerializedName("title")
    lateinit var title: String
    @SerializedName("date")
    lateinit var date: String

    @SerializedName("location")
    lateinit var location: Any

    @SerializedName("entrancePrice")
    var entrancePrice: Number = 0
    @SerializedName("organizers")
    lateinit var organizers: List<String>
    @SerializedName("guests")
    lateinit var guests: List<String>
    @SerializedName("description")
    lateinit var description: String


    @SerializedName("consumables")
    lateinit var consumables: List<String>
    @SerializedName("stuffs")
    lateinit var stuffs: List<String>
}
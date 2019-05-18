package com.example.amaze.network

import android.location.Location
import com.example.amaze.models.Consumable
import com.example.amaze.models.Guest
import com.example.amaze.models.Organizer
import com.example.amaze.models.Stuff
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

// Pour satisfaire le parser JSON
// ne pas mettre de constructeur custom

// Ne pas utiliser dans des vrais projets
// On demande au parseur d'ignorer les propriétés du Json
// qui ne sont pas définis dans la classe Kotlin
@JsonIgnoreProperties(ignoreUnknown = true)
class EventResult : Serializable{

    @SerializedName("title")
    lateinit var title: String
    @SerializedName("date")
    lateinit var date: String
    @SerializedName("dateIsFinal")
    var dateIsFinal: Boolean = false
    @SerializedName("location")
    lateinit var location: Any
    @SerializedName("locationIsFinal")
    var locationIsFinal: Boolean = false
    @SerializedName("entrancePrice")
    var entrancePrice: Number = 0
    @SerializedName("organizers")
    lateinit var organizers: List<Organizer>
    @SerializedName("guests")
    lateinit var guests: List<Guest>
    @SerializedName("description")
    lateinit var description: String
    @SerializedName("note")
    lateinit var note: String
    @SerializedName("guestsComming")
    lateinit var guestsComming: List<Guest>
    @SerializedName("guestsNotComming")
    lateinit var guestsNotComming: List<Guest>
    @SerializedName("guestsMaybe")
    lateinit var guestsMaybe: List<Guest>
    @SerializedName("_id")
    lateinit var id: String
    @SerializedName("createdAt")
    lateinit var createdAt: String
    @SerializedName("updatedAt")
    lateinit var updatedAt: String
    @SerializedName("consumables")
    lateinit var consumables: List<Consumable>
    @SerializedName("stuffs")
    lateinit var stuffs: List<Stuff>
}
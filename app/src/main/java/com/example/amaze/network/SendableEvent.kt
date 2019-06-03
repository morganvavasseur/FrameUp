package com.example.amaze.network

import com.example.amaze.models.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SendableEvent : Serializable, AbstractEvent {

    @SerializedName("organizers")
    lateinit var organizers: ArrayList<String>
    @SerializedName("guests")
    lateinit var guests: ArrayList<String>

    @SerializedName("consumables")
    lateinit var consumables: ArrayList<String>
    @SerializedName("stuffs")
    lateinit var stuffs: ArrayList<String>

    @SerializedName("guestsComming")
    lateinit var guestsComming: ArrayList<String>
    @SerializedName("guestsNotComming")
    lateinit var guestsNotComming: ArrayList<String>
    @SerializedName("guestsMaybe")
    lateinit var guestsMaybe: ArrayList<String>

    constructor() {

    }

    constructor(eventResult : EventResult) {
        super.title = eventResult.title
        super.date = eventResult.date
        super.dateIsFinal = eventResult.dateIsFinal
        super.location = eventResult.location
        super.description = eventResult.description
        super.id = eventResult.id
        super.locationIsFinal = eventResult.locationIsFinal
        super.entrancePrice = eventResult.entrancePrice
        this.guests = convertListOfPeronsToListOfString(eventResult.guests)
        this.organizers = convertListOfPeronsToListOfString(eventResult.organizers)
        this.guestsComming = convertListOfPeronsToListOfString(eventResult.guestsComming)
        this.guestsMaybe = convertListOfPeronsToListOfString(eventResult.guestsMaybe)
        this.guestsNotComming = convertListOfPeronsToListOfString(eventResult.guestsNotComming)

        this.consumables = convertListOfStuffToListOfString(eventResult.consumables)
        this.stuffs = convertListOfStuffToListOfString(eventResult.stuffs)
    }


    fun convertListOfPeronsToListOfString(persons: List<Person>) : ArrayList<String> {
        var personsList = ArrayList<String>()
        persons.forEach {
            personsList.add(it.id)
        }
        return personsList
    }

    fun convertListOfStuffToListOfString(persons: List<Stuff>) : ArrayList<String> {
        var personsList = ArrayList<String>()
        persons.forEach {
            personsList.add(it.id)
        }
        return personsList
    }

}
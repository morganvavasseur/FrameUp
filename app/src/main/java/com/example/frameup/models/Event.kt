package com.example.frameup.models
import android.location.Location
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

// Pour satisfaire le parser JSON
// ne pas mettre de constructeur custom

// Ne pas utiliser dans des vrais projets
// On demande au parseur d'ignorer les propriétés du Json
// qui ne sont pas définis dans la classe Kotlin
@JsonIgnoreProperties(ignoreUnknown = true)
class Event {

    // Titre de l'event
    lateinit var title: String

    // Date présumé de l'event
    lateinit var date: Date

    // Vrai si la date de l'event est validée
    var dateIsFinal: Boolean = false

    // Contient le lieu de l'event
    lateinit var location: Location

    // Vrai si le lieu de l'event est validé
    var locationIsFinal: Boolean = false

    // Prix de participation de l'event pour
    // chaque invité
    var entrancePrice: Int = 0

    // Liste des organisateurs de l'event
    lateinit var organizers: MutableList<User>

    // Liste des invités
    lateinit var guests: MutableList<User>

    // Liste des Consumables de l'event
    lateinit var consumables: MutableList<Consumable>

    // Liste des Stuffs de l'event
    lateinit var stuffs: MutableList<Stuff>
}
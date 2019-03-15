package com.example.amaze.models
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

// Pour satisfaire le parser JSON
// ne pas mettre de constructeur custom

// Ne pas utiliser dans des vrais projets
// On demande au parseur d'ignorer les propriétés du Json
// qui ne sont pas définis dans la classe Kotlin
@JsonIgnoreProperties(ignoreUnknown = true)
class Event {

    @JsonProperty("_id")
    lateinit var id: String

    // Titre de l'event
    lateinit var title: String

    // Date présumé de l'event
    lateinit var date: String

    // Vrai si la date de l'event est validée
    var dateIsFinal: Boolean = false

    // Contient le lieu de l'event
    //lateinit var location: Location

    // Vrai si le lieu de l'event est validé
    var locationIsFinal: Boolean = false

    // Prix de participation de l'event pour
    // chaque invité
    var entrancePrice: Int = 0

    // Liste des organisateurs de l'event
    lateinit var organizers: Array<User>

    // Liste des invités
    lateinit var guests: Array<User>

    // Liste des Consumables de l'event
    var consumables: Array<Consumable>? = null

    // Liste des Stuffs de l'event
    lateinit var stuffs: Array<Stuff>
}
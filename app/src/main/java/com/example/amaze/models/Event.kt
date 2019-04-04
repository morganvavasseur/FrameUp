package com.example.amaze.models
import android.location.Location
import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

// Pour satisfaire le parser JSON
// ne pas mettre de constructeur custom

// Ne pas utiliser dans des vrais projets
// On demande au parseur d'ignorer les propriétés du Json
// qui ne sont pas définis dans la classe Kotlin
@JsonIgnoreProperties(ignoreUnknown = true)
data class Event(

    @JsonProperty("_id")
    val id: String,

    // Titre de l'event
    val title: String,

    // Date présumé de l'event
    val date: Date,

    // Personne qui à créer l'event
    //val host: User,

    // Description de l'event
    val description: String,

    // Vrai si la date de l'event est validée
    var dateIsFinal: Boolean = false

    // Contient le lieu de l'event
    //val location: String

    // Vrai si le lieu de l'event est validé
    //var locationIsFinal: Boolean = false,

    // Prix de participation de l'event pour
    // chaque invité
    //var entrancePrice: Int = 0,

    // Liste des organisateurs de l'event
    //val organizers: Array<User>,

    // Liste des invités
    //val guests: Array<User>,

    // Liste des Consumables de l'event
    //var consumables: Array<Consumable>? = null,

    // Liste des Stuffs de l'event
    //val stuffs: Array<Stuff>
)
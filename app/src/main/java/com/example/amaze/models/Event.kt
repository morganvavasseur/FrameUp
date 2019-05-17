package com.example.amaze.models
import android.location.Location
import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.util.*

// Pour satisfaire le parser JSON
// ne pas mettre de constructeur custom

// Ne pas utiliser dans des vrais projets
// On demande au parseur d'ignorer les propriétés du Json
// qui ne sont pas définis dans la classe Kotlin
@JsonIgnoreProperties(ignoreUnknown = true)
class Event : Serializable {

    // Titre de l'event
    lateinit var title: String

    // Date présumé de l'event
    lateinit var date: String

    // Personne qui à créer l'event
    //val host: User,

    // Description de l'event
    lateinit var description: String

    // Vrai si la date de l'event est validée
    var dateIsFinal: Boolean = false

    // Contient le lieu de l'event
    lateinit var location: Any

    // Vrai si le lieu de l'event est validé
    var locationIsFinal: Boolean = false

    // Prix de participation de l'event pour
    // chaque invité
    lateinit var entrancePrice: Number

    // Liste des organisateurs de l'event
    lateinit var organizers: ArrayList<User>

    // Liste des invités
    //val guests: Array<User>,

    // Liste des Consumables de l'event
    //var consumables: Array<Consumable>? = null,

    // Liste des Stuffs de l'event
    //val stuffs: Array<Stuff>

}
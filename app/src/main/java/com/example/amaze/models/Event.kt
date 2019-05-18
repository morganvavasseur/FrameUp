package com.example.amaze.models
import com.example.amaze.network.StuffResult
import com.example.amaze.network.UserResult
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable
import kotlin.collections.ArrayList

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
    lateinit var organizers: ArrayList<UserResult>

    // Liste des invités
    lateinit var guests: Array<UserResult>

    // Description de l'event
    lateinit var description: String

    // Note pour l'event
    lateinit var note: String

    // Les invités qui ont indiqués venir
    lateinit var guestsComming : ArrayList<UserResult>

    // Les invités qui ont indiqué ne pas venir
    lateinit var guestsNotComming : ArrayList<UserResult>

    // Les invités qui ont indiqué ne pas savoir si ils venaient
    lateinit var guestsMaybe : ArrayList<UserResult>

    // Liste des Consumables de l'event
    lateinit var consumables: ArrayList<Consumable>

    // Liste des Stuffs de l'event
    lateinit var stuffs: ArrayList<StuffResult>

}
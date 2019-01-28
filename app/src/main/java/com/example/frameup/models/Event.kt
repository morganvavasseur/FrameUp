package com.example.frameup.models
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

    lateinit var title: String

    lateinit var date: Date

    @JsonProperty("date_isFinal")
    var dateIsFinal: Boolean = false
}
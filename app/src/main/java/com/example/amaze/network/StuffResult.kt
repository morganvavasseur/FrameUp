package com.example.amaze.network

import com.example.amaze.models.Stuff
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
open class StuffResult {

    @JsonProperty("_id")
    lateinit var id: String

    // Nom de l'objet
    lateinit var name: String

    // Quantité nécessaire
    var quantity: Int = 0

    // Description de l'objet
    lateinit var description: String

    // Anniversaire auquel est lié le Stuff
    lateinit var event: String
}
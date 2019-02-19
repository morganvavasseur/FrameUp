package com.example.frameup.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.jar.Attributes

@JsonIgnoreProperties(ignoreUnknown = true)
open class Stuff {

    @JsonProperty("_id")
    lateinit var id: String

    lateinit var createdAt: String

    lateinit var updatedAt: String

    // Nom de l'objet
    lateinit var name: String

    // Quantité nécessaire
    var quantity: Int = 0

    // Description de l'objet
    lateinit var description: String

    // Invités qui ont dit vouloir se placer sur un Stuff
    var usersOnIt: Array<String>? = null

    // Anniversaire auquel est lié le Stuff
    lateinit var event: String



}
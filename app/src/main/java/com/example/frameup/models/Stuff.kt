package com.example.frameup.models

import java.util.jar.Attributes

open class Stuff {

    // Nom de l'objet
    lateinit var name: Attributes.Name

    // Quantité nécessaire
    var quantity: Int = 0

    // Description de l'objet
    lateinit var description: String

    // Invités qui ont dit vouloir se placer sur un Stuff
    lateinit var usersOnIt: MutableList<User>

    // Anniversaire auquel est lié le Stuff
    lateinit var event: Event



}
package com.example.frameup.models

import android.provider.ContactsContract
import java.util.*
import java.util.jar.Attributes

class User {

    // Prénom de l'utilisateur
    lateinit var firstName: Attributes.Name

    // Nom de l'utilisateur
    lateinit var lastName: Attributes.Name

    // Date d'anniversaire de l'utilisateur
    lateinit var birthday: Date

    // Numéro de téléphone de l'utilisateur
    lateinit var phone: ContactsContract.CommonDataKinds.Phone

    // Stuff sur lesquels l'utilisateur est présent dans les différents event
    lateinit var stuffs: MutableList<Stuff>

    // Consumables sur lesquels l'utilisateur est présent dans les différents event
    lateinit var consumables: MutableList<Consumable>
}

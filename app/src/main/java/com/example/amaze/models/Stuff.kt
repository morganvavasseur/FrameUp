package com.example.amaze.models

import com.example.amaze.network.EventResult
import com.example.amaze.network.UserResult
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.jar.Attributes

@JsonIgnoreProperties(ignoreUnknown = true)
open class Stuff : Serializable {

    @SerializedName("name")
    lateinit var name: String
    @SerializedName("quantity")
    var quantity: Int = 0
    @SerializedName("description")
    lateinit var description: String
    @SerializedName("usersOnIt")
    lateinit var usersOnIt: List<String>

    @SerializedName("_id")
    lateinit var id: String
    @SerializedName("createdAt")
    lateinit var createdAt: String
    @SerializedName("updatedAt")
    lateinit var updatedAt: String
    @SerializedName("event")
    lateinit var event: String
}
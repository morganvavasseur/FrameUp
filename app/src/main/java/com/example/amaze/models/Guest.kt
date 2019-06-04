package com.example.amaze.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class Guest : Person(), Serializable {

    @SerializedName("stuffs")
    lateinit var stuffs: List<String>

    @SerializedName("provider")
    lateinit var provider: String
    @SerializedName("role")
    lateinit var role: String

}
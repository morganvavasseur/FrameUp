package com.example.amaze.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
class Consumable : Stuff() {

    @SerializedName("price")
    var price: Int = 0
}
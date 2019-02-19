package com.example.frameup.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
class Consumable : Stuff() {
    var price: Int = 0
}
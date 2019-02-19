package com.example.frameup.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.jar.Attributes


@JsonIgnoreProperties(ignoreUnknown = true)
class Diet {

    @JsonProperty("_id")
    lateinit var id: String

    lateinit var name: String
    lateinit var description: String
}
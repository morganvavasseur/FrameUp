package com.example.amaze.models

import com.google.gson.annotations.SerializedName

data class Role (

    @SerializedName("_id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("type") val type : String
)
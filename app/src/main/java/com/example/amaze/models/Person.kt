package com.example.amaze.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Person : Serializable {
    @SerializedName("_id")
    lateinit var id: String
    @SerializedName("confirmed")
    var confirmed: Boolean = false
    @SerializedName("blocked")
    var blocked: Boolean = false
    @SerializedName("dietOther")
    lateinit var dietOther: String
    @SerializedName("firstName")
    lateinit var firstName: String
    @SerializedName("lastName")
    lateinit var lastName: String
    @SerializedName("birthday")
    lateinit var birthday: String
    @SerializedName("username")
    lateinit var username : String
    @SerializedName("email")
    lateinit var email : String
    @SerializedName("phone")
    lateinit var phone: String


    fun fullName() : String {
        var fullName : String = ""
        if (this.firstName != null) {
            fullName += this.firstName
            if (this.lastName != null)
                fullName += " " + this.lastName
        }
        else
            fullName = "NAME IS NULL"

        return fullName

    }
}
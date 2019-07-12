package com.example.registrationapp.model

import android.location.Location
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class People : Serializable {

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("name")
    var name: Name? = null

    @SerializedName("location")
    var location: com.example.registrationapp.model.Location? = null

    @SerializedName("email")
    var mail: String? = null

    @SerializedName("login")
    var login: Login? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("cell")
    var cell: String? = null

    @SerializedName("picture")

    lateinit var picture: Picture

    var fullName: String = ""

    fun hasEmail(): Boolean {
        return mail != null && !mail!!.isEmpty()
    }
}



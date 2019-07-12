package com.example.registrationapp.data

import com.example.registrationapp.model.People
import com.google.gson.annotations.SerializedName

class PeopleResponce {
    @SerializedName("results")
    val peopleList: List<People>? = null
}
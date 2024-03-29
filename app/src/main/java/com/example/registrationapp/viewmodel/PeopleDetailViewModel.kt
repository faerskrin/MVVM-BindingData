package com.example.registrationapp.viewmodel

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.registrationapp.model.People

class PeopleDetailViewModel(private val people: People) {

    val fullUserName: String
        get() {
            people.fullName = people.name!!.title + "." + people.name!!.firts + " " + people.name!!.last
            return people.fullName
        }

    val userName: String
        get() = people.login!!.userName!!

    val email: String
        get() = people.mail!!

    val emailVisibility: Int
        get() = if (people.hasEmail()) View.VISIBLE else View.GONE

    val cell: String
        get() = people.cell!!

    val pictureProfile: String
        get() = people.picture.large!!

    val address: String
        get() = (people.location!!.street
                + " "
                + people.location!!.city
                + " "
                + people.location!!.state)

    val gender: String
        get() = people.gender!!

    companion object {

//        @BindingAdapter("imageUrl")
//        fun loadImage(view: ImageView, imageUrl: String) {
//            Glide.with(view.context).load(imageUrl).into(view)
//        }
    }
}
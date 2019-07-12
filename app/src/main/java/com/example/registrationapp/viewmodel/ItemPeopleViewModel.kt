package com.example.registrationapp.viewmodel

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import com.example.registrationapp.model.People
import android.R
import com.example.registrationapp.view.PeopleDetailActivity
import com.squareup.picasso.Picasso


class ItemPeopleViewModel(private var people: People?, private val context: Context) : BaseObservable() {


    val fullName: String
        get() {
            people!!.fullName = people!!.name?.title + "." + people!!.name?.firts + " " + people!!.name?.last
            return people!!.fullName
        }

    val cell: String?
        get() = people!!.cell

    val mail: String
        get() = people!!.mail!!

    val pictureProfile: String?
        get() = people?.picture?.medium


    //TODO HERE I HAVE EXEPTION
    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, url: String?) {
        Picasso.get().load("https://randomuser.me/api/portraits/med/women/35.jpg").error(R.drawable.stat_notify_error).into(imageView)

        //Glide.with(imageView.context).load(url).apply(RequestOptions().circleCrop()).into(imageView)
    }

    fun onItemClick(view: View) {
      //  Toast.makeText(context,"Start",Toast.LENGTH_SHORT).show()
        context.startActivity(PeopleDetailActivity.launchDetail(view.context, people))
    }

    fun setPeople(people: People) {
        this.people = people
        notifyChange()
    }
    companion object {
    }
}
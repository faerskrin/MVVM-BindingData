package com.example.registrationapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.registrationapp.R
import com.example.registrationapp.databinding.PeopleDetailActivityBinding
import com.example.registrationapp.model.People
import com.example.registrationapp.viewmodel.PeopleDetailViewModel

class PeopleDetailActivity: AppCompatActivity() {

    private var peopleDetailActivityBinding: PeopleDetailActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        peopleDetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.people_detail_activity)
        setSupportActionBar(peopleDetailActivityBinding!!.toolbar)
        displayHomeAsUpEnabled()
        getExtrasFromIntent()
    }

    private fun displayHomeAsUpEnabled() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getExtrasFromIntent() {
        val people = intent.getSerializableExtra(EXTRA_PEOPLE) as People
        val peopleDetailViewModel = PeopleDetailViewModel(people)
        peopleDetailActivityBinding!!.peopleDetailViewModel = peopleDetailViewModel
        title = people.name!!.title + "." + people.name!!.firts + " " + people.name!!.last
    }

    companion object {

        private val EXTRA_PEOPLE = "EXTRA_PEOPLE"

        fun launchDetail(context: Context, people: People?): Intent {
            val intent = Intent(context, PeopleDetailActivity::class.java)
            intent.putExtra(EXTRA_PEOPLE, people)
            return intent
        }
    }
}
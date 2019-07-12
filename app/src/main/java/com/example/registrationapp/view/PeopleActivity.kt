package com.example.registrationapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registrationapp.R
import com.example.registrationapp.databinding.PeopleActivityBinding
import com.example.registrationapp.viewmodel.PeopleViewModel
import java.util.*

class PeopleActivity : AppCompatActivity(),Observer {


    var peopleActivityBinding: PeopleActivityBinding?=null
    private var peopleViewModel: PeopleViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.people_activity)
        initDataBinding()
        setSupportActionBar(peopleActivityBinding!!.toolbar)
        setupListPeopleView(peopleActivityBinding!!.listPeople)
        setupObserver(peopleViewModel)

    }

    private fun setupListPeopleView(listPeople: RecyclerView) {
        val adapter = PeopleAdapter()
        listPeople.adapter = adapter
        listPeople.layoutManager = LinearLayoutManager(this)
    }

    fun setupObserver(observable: Observable?) {
        observable!!.addObserver(this)
    }

    private fun initDataBinding() {
        peopleActivityBinding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.people_activity) as PeopleActivityBinding?
        peopleViewModel = PeopleViewModel(this)
        peopleActivityBinding!!.mainViewModel = peopleViewModel
    }



    override fun update(observable: Observable, data: Any?) {
        if (observable is PeopleViewModel) {
            val peopleAdapter = peopleActivityBinding!!.listPeople.adapter as PeopleAdapter?
            peopleAdapter!!.setPeopleList(observable.getPeopleList())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        peopleViewModel!!.reset()
    }


}

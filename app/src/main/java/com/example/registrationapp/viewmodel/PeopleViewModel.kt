package com.example.registrationapp.viewmodel

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.registrationapp.App
import com.example.registrationapp.R
import com.example.registrationapp.data.PeopleFactory
import com.example.registrationapp.model.People
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import java.util.*
import kotlin.collections.ArrayList

class PeopleViewModel(private var context: Context?): Observable() {

    var peopleProgress: ObservableInt= ObservableInt(View.GONE)
    var peopleRecycler: ObservableInt= ObservableInt(View.GONE)

    private val peopleList: MutableList<People>
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    init {
        this.peopleList = ArrayList<People>()
        peopleProgress = ObservableInt(View.GONE)
        initializeViews()
        fetchPeopleList()
    }

    fun onClickFabLoad(view: View) {
        initializeViews()
        fetchPeopleList()
    }

    fun initializeViews() {
        peopleRecycler.set(View.GONE)
        peopleProgress.set(View.VISIBLE)
    }

    private fun fetchPeopleList() {

        val peopleApplication = App.create(context!!)
        val peopleService = peopleApplication.getPeopleService()

        val disposable = peopleService!!.fetchPeople(PeopleFactory.RANDOM_USER_URL)
            .subscribeOn(peopleApplication.subscribeScheduler()!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ peopleResponse ->
                changePeopleDataSet(peopleResponse.peopleList!!)
                peopleProgress.set(View.GONE)
                peopleRecycler.set(View.VISIBLE)
            }, { throwable ->
                peopleProgress.set(View.GONE)
                peopleRecycler.set(View.GONE)
                throwable.printStackTrace()
            })

        compositeDisposable!!.add(disposable)
    }


    private fun changePeopleDataSet(peoples: List<People>) {
        peopleList.addAll(peoples)
        setChanged()
        notifyObservers()
    }

    fun getPeopleList(): List<People> {
        return peopleList
    }

    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }

    fun reset() {
        unSubscribeFromObservable()
        compositeDisposable = null
        context = null
    }
}
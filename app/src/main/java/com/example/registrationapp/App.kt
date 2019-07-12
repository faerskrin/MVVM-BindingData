package com.example.registrationapp

import android.app.Application
import android.content.Context
import com.example.registrationapp.data.PeopleFactory
import com.example.registrationapp.data.PeopleService
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

public class App: Application() {

    private var scheduler:Scheduler? = null
    private var peopleService: PeopleService? = null


    fun getPeopleService(): PeopleService? {
        if (peopleService == null) {
            peopleService = PeopleFactory.create()
        }

        return peopleService
    }

    fun subscribeScheduler(): Scheduler? {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }

        return scheduler
    }

    companion object {

        private operator fun get(context: Context?): App {
            return context?.applicationContext as App
        }

        fun create(context: Context?): App {
            return App[context]
        }
    }
}
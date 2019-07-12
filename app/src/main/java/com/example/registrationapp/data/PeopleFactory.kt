package com.example.registrationapp.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object PeopleFactory {
    private val BASE_URL= "https://api.randomuser.me/"
    val RANDOM_USER_URL = "https://api.randomuser.me/?results=10&nat=en"
//    val PROJECT_URL = "https://github.com/erikjhordan-rey/People-MVVM"

    public fun create(): PeopleService {

        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(15,TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .readTimeout(15,TimeUnit.SECONDS)
            .writeTimeout(15,TimeUnit.SECONDS)
            .build()


        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(PeopleService::class.java)
    }
}
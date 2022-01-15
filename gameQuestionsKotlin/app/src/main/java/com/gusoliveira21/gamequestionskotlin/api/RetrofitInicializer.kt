package com.gusoliveira21.gamequestionskotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInicializer {
    companion object{
        const val BASE_URL = "https://raw.githubusercontent.com/"
        //const val BASE_URL = "http://webservice.eu5.org"
    }
    private fun questionProvider():Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun questionsService(): RetrofitService = questionProvider().create(RetrofitService::class.java)


}
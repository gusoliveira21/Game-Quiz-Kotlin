package com.gusoliveira21.gamequestionskotlin.api

import com.gusoliveira21.gamequestionskotlin.model.QuestionsKotlin
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    //https://raw.githubusercontent.com/gusoliveira21/Game-Quiz-Kotlin/main/lista-perguntas.txt
    //http://webservice.eu5.org/quiz_kotlin/quiz_kotlin_questions.html


    @GET("/gusoliveira21/Game-Quiz-Kotlin/main/lista-perguntas")
    fun list():Call<MutableList<QuestionsKotlin>>

    
}
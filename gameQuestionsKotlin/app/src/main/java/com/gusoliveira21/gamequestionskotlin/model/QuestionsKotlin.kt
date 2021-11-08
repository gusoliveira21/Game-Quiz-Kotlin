package com.gusoliveira21.gamequestionskotlin.model

import com.google.gson.annotations.SerializedName

data class QuestionsKotlin(
    @SerializedName("id")
    val id: Int,
    @SerializedName("question")
    val pergunta: String,
    @SerializedName("topic_one")
    val um:String,
    @SerializedName("topic_two")
    val dois:String,
    @SerializedName("topic_three")
    val tres:String,
    @SerializedName("topic_four")
    val quatro:String,
    @SerializedName("topic_five")
    val cinco:String
) {

}

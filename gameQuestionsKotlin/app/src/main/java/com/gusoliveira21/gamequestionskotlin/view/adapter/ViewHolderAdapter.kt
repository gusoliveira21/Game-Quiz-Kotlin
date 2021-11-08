package com.gusoliveira21.gamequestionskotlin.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.gusoliveira21.gamequestionskotlin.databinding.ItemListaBinding
import com.gusoliveira21.gamequestionskotlin.model.QuestionsKotlin

class ViewHolderAdapter(binding: ItemListaBinding) : RecyclerView.ViewHolder(binding.root) {
    val question = binding.textView
    val option_one = binding.questionOne
    val option_two = binding.questionTwo
    val option_three = binding.questionThree
    val option_four = binding.questionFour
    val option_five = binding.questionFive



    fun enviarDadosParaView(lista: QuestionsKotlin) {
        question.setText("${lista.pergunta}")
        option_one.setText("${lista.pergunta}")
    }
}
}
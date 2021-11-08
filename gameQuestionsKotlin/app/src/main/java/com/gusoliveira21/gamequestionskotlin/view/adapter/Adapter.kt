package com.gusoliveira21.gamequestionskotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gusoliveira21.gamequestionskotlin.databinding.ItemListaBinding
import com.gusoliveira21.gamequestionskotlin.model.QuestionsKotlin

class Adapter(val lista_questoes:List<QuestionsKotlin>):RecyclerView.Adapter<ViewHolderAdapter>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAdapter {
        val binding = ItemListaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderAdapter(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderAdapter, position: Int) {
        val lista = lista_questoes[position]
        holder.enviarDadosParaView(lista)
    }

    override fun getItemCount() = lista_questoes.size


}
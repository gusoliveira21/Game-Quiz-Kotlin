package com.gusoliveira21.gamequestionskotlin.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gusoliveira21.gamequestionskotlin.api.RetrofitInicializer
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentGameBinding
import com.gusoliveira21.gamequestionskotlin.model.QuestionsKotlin
import com.gusoliveira21.gamequestionskotlin.view.adapter.Adapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class gameFragment : Fragment() {
    private val binding by lazy { FragmentGameBinding.inflate(LayoutInflater.from(context)) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val call = RetrofitInicializer().questionsService().list()
        call.enqueue(
            object : Callback<List<QuestionsKotlin>?> {
                override fun onResponse(call: Call<List<QuestionsKotlin>?>,response: Response<List<QuestionsKotlin>?>){
                    response?.body()?.let{
                        val questions_list: List<QuestionsKotlin> =it
                        configureList(questions_list)
                    }
                }
                override fun onFailure(call: Call<List<QuestionsKotlin>?>, t: Throwable) {
                    t?.message?.let{ Log.e("Erro ->>",it)}
                }
            }
        )
        return binding.root
    }

    private fun configureList(question_list: List<QuestionsKotlin>) {
        binding.recyclerview.adapter= Adapter(question_list/*,requireParentFragment().requireContext()*/)
        binding.recyclerview.layoutManager= StaggeredGridLayoutManager( 1, StaggeredGridLayoutManager.VERTICAL)
    }



    /*fun listener() {
        binding.btWin.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameFragment_to_winFragment)
        }
        binding.btGameOver.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameFragment_to_overFragment)
        }
    }*/

}
package com.gusoliveira21.gamequestionskotlin.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gusoliveira21.gamequestionskotlin.R
import com.gusoliveira21.gamequestionskotlin.api.RetrofitInicializer
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentGameBinding
import com.gusoliveira21.gamequestionskotlin.model.QuestionsKotlin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class gameFragment : Fragment() {
    private val binding by lazy { FragmentGameBinding.inflate(LayoutInflater.from(context)) }

    companion object {
        lateinit var questions_list: MutableList<QuestionsKotlin>
        lateinit var objetoQuestion: QuestionsKotlin
        var listOptionsOriginal = listOf<String>()
        var listOptionsRandom = listOf<String>()
        var pontuacao: Int = 0
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        getListRetrofit()
        listener()

        return binding.root
    }

    private fun getListRetrofit() {
        val call = RetrofitInicializer().questionsService().list()
        call.enqueue(
            object : Callback<MutableList<QuestionsKotlin>?> {
                override fun onResponse(
                    call: Call<MutableList<QuestionsKotlin>?>,
                    response: Response<MutableList<QuestionsKotlin>?>,
                ) {
                    response?.body()?.let {
                        questions_list = it
                        showQuestions(questions_list)
                    }
                }

                override fun onFailure(call: Call<MutableList<QuestionsKotlin>?>, t: Throwable) {
                    t?.message?.let { Log.e("Error Retrofit ->", it) }
                }
            }
        )
    }

    private fun showQuestions(questions_list: MutableList<QuestionsKotlin>) {
        objetoQuestion = EscolheUmaQuestionAleatoria(questions_list)[0]
        binding.pergunta.text = objetoQuestion.pergunta

        listOptionsOriginal =
            listOf(
                objetoQuestion.um,
                objetoQuestion.dois,
                objetoQuestion.tres,
                objetoQuestion.quatro
            )
        listOptionsRandom = listOptionsOriginal.shuffled()

        binding.radioZero.setText(listOptionsRandom[0])
        binding.radioUm.setText(listOptionsRandom[1])
        binding.radioDois.setText(listOptionsRandom[2])
        binding.radioTres.setText(listOptionsRandom[3])
    }

    private fun EscolheUmaQuestionAleatoria(questions_list: MutableList<QuestionsKotlin>): MutableList<QuestionsKotlin> {
        val questions_list_random = questions_list.shuffled()
        val list = questions_list_random.toMutableList()
        return list
    }

    fun listener() {
        binding.submit.setOnClickListener {
            verificaRadioButton()
        }
    }

    fun verificaRadioButton() {
        when {
            binding.radioZero.isChecked -> verificaQuestaoCorreta(0)
            binding.radioUm.isChecked -> verificaQuestaoCorreta(1)
            binding.radioDois.isChecked -> verificaQuestaoCorreta(2)
            binding.radioTres.isChecked -> verificaQuestaoCorreta(3)
        }
    }

    fun verificaQuestaoCorreta(index: Int) {
        if (listOptionsRandom[index].equals(listOptionsOriginal[0])) {
            pontuacao++
            binding.point.setText(pontuacao.toString())
            verificaPontuacao()
            questions_list.remove(objetoQuestion)
            showQuestions(questions_list)
            binding.radioGroup.clearCheck()
        } else {
            pontuacao--
            binding.point.setText(pontuacao.toString())
            verificaPontuacao()
            showQuestions(questions_list)
            binding.radioGroup.clearCheck()

        }

    }

    private fun verificaPontuacao() {
        if(pontuacao.equals(3)) {
            findNavController().navigate(R.id.action_gameFragment_to_winFragment)
        }
        else if(pontuacao.equals(-1)) {
            findNavController().navigate(R.id.action_gameFragment_to_overFragment)
        }
    }


}



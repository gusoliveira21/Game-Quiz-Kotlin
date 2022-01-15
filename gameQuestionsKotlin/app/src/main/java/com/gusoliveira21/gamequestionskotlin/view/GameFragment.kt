package com.gusoliveira21.gamequestionskotlin.view

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gusoliveira21.gamequestionskotlin.api.RetrofitInicializer
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentGameBinding
import com.gusoliveira21.gamequestionskotlin.model.QuestionsKotlin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameFragment : Fragment() {
    private val binding by lazy { FragmentGameBinding.inflate(LayoutInflater.from(context)) }

    companion object {
        var questions_list: MutableList<QuestionsKotlin> = mutableListOf(QuestionsKotlin("","","","",""))

        lateinit var dialog:ProgressDialog
        lateinit var objetoQuestion: QuestionsKotlin
        var listOptionsOriginal = listOf<String>()
        var listOptionsRandom = listOf<String>()
        var pontuacao: Int = 1

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        try {
            showLoadingDialog("Obtendo dados ...")
            getListRetrofit()
            listener()

        }catch (e:Exception){
            Toast.makeText(context,"Erro: $e",Toast.LENGTH_LONG).show()
        }
        return binding.root
    }

    private fun showLoadingDialog(message: String):ProgressDialog {
        dialog = ProgressDialog(context)
        dialog.setMessage("Obtendo dados ...")
        dialog.show()
        return dialog
    }
    private fun hideLoadingDialog():ProgressDialog {
        dialog.cancel()
        return dialog
    }

    private fun getListRetrofit() {
        val call = RetrofitInicializer().questionsService().list()
        call.enqueue(
            object : Callback<MutableList<QuestionsKotlin>?> {
                override fun onResponse(
                    call: Call<MutableList<QuestionsKotlin>?>,
                    response: Response<MutableList<QuestionsKotlin>?>,
                ) {
                    response.body()?.let {
                        hideLoadingDialog()
                        questions_list.clear()
                        questions_list = it
                        showQuestions(questions_list)
                    }
                }

                override fun onFailure(call: Call<MutableList<QuestionsKotlin>?>, t: Throwable) {
                    t.message?.let { Log.e("Error Retrofit ->", it) }
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

        binding.radioZero.text = listOptionsRandom[0]
        binding.radioUm.text = listOptionsRandom[1]
        binding.radioDois.text = listOptionsRandom[2]
        binding.radioTres.text = listOptionsRandom[3]
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
            verificaPontuacao()
            binding.point.text = pontuacao.toString()
            questions_list.remove(objetoQuestion)
            showQuestions(questions_list)
            binding.radioGroup.clearCheck()
        } else {
            pontuacao--
            verificaPontuacao()
            binding.point.text = pontuacao.toString()
            showQuestions(questions_list)
            binding.radioGroup.clearCheck()

        }

    }

    private fun verificaPontuacao() {
        if(pontuacao.equals(4)) {
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToWinFragment(pontuacao-1))
            pontuacao=1
        }
        else if(pontuacao.equals(0)) {
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToOverFragment())
            pontuacao=1
        }
    }


}



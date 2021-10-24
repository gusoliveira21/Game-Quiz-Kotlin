package com.gusoliveira21.gamequestionskotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentGameBinding
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentHomeBinding

class gameFragment : Fragment() {
    private val binding by lazy { FragmentGameBinding.inflate(LayoutInflater.from(context)) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        listener()
        return binding.root
    }

    fun listener() {
        binding.btWin.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameFragment_to_winFragment)
        }
        binding.btGameOver.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameFragment_to_overFragment)
        }
    }

}
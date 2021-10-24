package com.gusoliveira21.gamequestionskotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentHomeBinding
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentWinBinding

class winFragment : Fragment() {
    private val binding by lazy { FragmentWinBinding.inflate(LayoutInflater.from(context))}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        listener()
        return binding.root
    }

    private fun listener(){
        binding.tvWin.setOnClickListener {
            it.findNavController().navigate(R.id.action_winFragment_to_homeFragment)
        }
    }
}
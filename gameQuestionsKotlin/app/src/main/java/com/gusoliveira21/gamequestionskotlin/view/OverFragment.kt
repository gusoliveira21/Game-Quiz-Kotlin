package com.gusoliveira21.gamequestionskotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.gusoliveira21.gamequestionskotlin.R
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentOverBinding

class OverFragment : Fragment() {
    private val binding by lazy { FragmentOverBinding.inflate(LayoutInflater.from(context)) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        listener()
        return binding.root
    }

    private fun listener() {
        binding.btTryAgain.setOnClickListener {
            it.findNavController().navigate(
OverFragmentDirections.actionOverFragmentToGameFragment()
            )
        }

        binding.btToHome.setOnClickListener {
            it.findNavController().navigate(
                OverFragmentDirections.actionOverFragmentToHomeFragment()
            )
        }
    }
}
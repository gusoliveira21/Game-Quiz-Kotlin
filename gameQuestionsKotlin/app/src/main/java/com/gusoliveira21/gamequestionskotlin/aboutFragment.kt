package com.gusoliveira21.gamequestionskotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentAboutBinding
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentHomeBinding

class aboutFragment : Fragment() {
    private val binding by lazy { FragmentAboutBinding.inflate(LayoutInflater.from(context))}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return binding.root
    }
}
package com.gusoliveira21.gamequestionskotlin.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gusoliveira21.gamequestionskotlin.R
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentAboutBinding
class aboutFragment : Fragment() {
    private val binding by lazy { FragmentAboutBinding.inflate(LayoutInflater.from(context))}
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?, ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }
}
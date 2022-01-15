package com.gusoliveira21.gamequestionskotlin.view

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.gusoliveira21.gamequestionskotlin.R
import com.gusoliveira21.gamequestionskotlin.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(LayoutInflater.from(context)) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        listeners()
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
                super.onOptionsItemSelected(item)
    }

    fun isOnline(context: Context): Boolean {
        val connectivity_manager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val active_network = connectivity_manager.activeNetworkInfo
        if (active_network != null) {
            return true// connected to the internet
            if (active_network.type == ConnectivityManager.TYPE_WIFI) {
                return true// connected to wifi
            } else if (active_network.type == ConnectivityManager.TYPE_MOBILE) {
                return true// connected to mobile data
            }
        } else {
            return false// not connected to the internet
        }
    }


     fun onSNACK(view: View){
         //Snackbar(view)
         val snackbar = Snackbar.make(view, "Sem conexão com a internet!",
             Snackbar.LENGTH_LONG).setAction("Action", null)
         snackbar.setActionTextColor(Color.YELLOW)
         val snackbarView = snackbar.view
         snackbarView.setBackgroundColor(Color.BLACK)
         val textView =
             snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
         textView.setTextColor(Color.YELLOW)
         textView.textSize = 28f
         snackbar.show()
     }

    private fun listeners() {
        binding.btPlay.setOnClickListener {
            if (isOnline(requireContext())) {
                it.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToGameFragment()
                )
            } else
                onSNACK(it)
                //Toast.makeText(requireContext(), "Sem conexão", Toast.LENGTH_LONG).show()
        }
    }

}



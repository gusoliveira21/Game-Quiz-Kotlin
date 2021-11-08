package com.gusoliveira21.gamequestionskotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.gusoliveira21.gamequestionskotlin.R
import com.gusoliveira21.gamequestionskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(LayoutInflater.from(this))}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navController = this.findNavController(R.id.navControlerID)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navControlerID)
        return navController.navigateUp()
    }
}
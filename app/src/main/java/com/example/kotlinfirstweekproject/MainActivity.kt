package com.example.kotlinfirstweekproject

import android.os.Bundle
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinfirstweekproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav : BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initViews()
        initEvents()

    }

    fun initViews(){
        bottomNav = binding.bottomNav

        val fragmentManager = supportFragmentManager
        navHostFragment = fragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    }

    fun initEvents(){
        bottomNav.setupWithNavController(navHostFragment.navController)
    }

}
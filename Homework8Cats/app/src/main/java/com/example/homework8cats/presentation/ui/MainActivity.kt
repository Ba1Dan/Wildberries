package com.example.homework8cats.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.homework8cats.R
import com.example.homework8cats.core.App
import com.example.homework8cats.databinding.ActivityMainBinding
import com.example.homework8cats.presentation.util.NetworkManager
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var networkManager: NetworkManager

    private val component by lazy { (application as App).component }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        component.inject(this)
        networkManager.registerCallback()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        networkManager.isConnectedNetwork.observe(this) { network ->
            binding.notification.root.isVisible = !network
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        networkManager.unregisterCallback()
    }
}
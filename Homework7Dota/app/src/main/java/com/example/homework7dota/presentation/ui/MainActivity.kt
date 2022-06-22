package com.example.homework7dota.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.homework5dota.presentation.util.NetworkManager
import com.example.homework7dota.R
import com.example.homework7dota.core.App
import com.example.homework7dota.databinding.ActivityMainBinding
import com.example.homework7dota.presentation.ui.list.HeroesFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val component by lazy { (application as App).component }
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var networkManager: NetworkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, HeroesFragment.newInstance())
                .commit()
        }

        observeNetworkState()
    }

    override fun onDestroy() {
        super.onDestroy()
        networkManager.unregisterCallback()
    }

    private fun init() {
        component.inject(this)
        networkManager.registerCallback()
    }

    private fun observeNetworkState() {
        networkManager.isConnectedNetwork.observe(this) { network ->
            binding.notification.root.isVisible = !network
        }
    }
}
package com.example.homework8dota.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.homework8dota.R
import com.example.homework8dota.core.App
import com.example.homework8dota.databinding.ActivityMainBinding
import com.example.homework8dota.presentation.ui.list.HeroesFragment
import com.example.homework8dota.presentation.util.NetworkManager
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

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, HeroesFragment.newInstance())
                .commit()
        }

        networkManager.isConnectedNetwork.observe(this) { network ->
            binding.notification.root.isVisible = !network
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        networkManager.unregisterCallback()
    }
}
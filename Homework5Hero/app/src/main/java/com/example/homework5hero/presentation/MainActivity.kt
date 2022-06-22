package com.example.homework5hero.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.homework5hero.R
import com.example.homework5hero.core.App
import com.example.homework5hero.databinding.ActivityMainBinding
import com.example.homework5hero.presentation.list.HeroesFragment
import com.example.homework5hero.presentation.util.NetworkManager
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
}
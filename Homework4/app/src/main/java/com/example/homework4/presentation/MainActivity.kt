package com.example.homework4.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework4.R
import com.example.homework4.databinding.ActivityMainBinding
import com.example.homework4.presentation.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, MainFragment.newInstance())
                .commit()
        }
    }
}
package com.example.homework5hero.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework5hero.R
import com.example.homework5hero.presentation.list.ListHeroesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, ListHeroesFragment.newInstance())
                .commit()
        }
    }
}
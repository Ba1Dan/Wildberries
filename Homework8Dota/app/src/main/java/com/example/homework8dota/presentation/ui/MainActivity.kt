package com.example.homework8dota.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework8dota.R
import com.example.homework8dota.presentation.ui.list.HeroesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, HeroesFragment.newInstance())
                .commit()
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        supportFragmentManager.popBackStack()
//        return true
//    }
}
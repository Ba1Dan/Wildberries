package com.example.homework3.without

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework3.R

class CalculatorActivityWithoutConstraint : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_without_constraint)
        supportActionBar?.hide()
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, CalculatorActivityWithoutConstraint::class.java)
        }
    }
}
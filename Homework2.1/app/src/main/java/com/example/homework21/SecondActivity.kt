package com.example.homework21

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton

class SecondActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.d(TAG, "onCreate")

        val btnOpen: MaterialButton = findViewById(R.id.btn_open)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnOpen.setOnClickListener {
            showAlertDialog()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.setTitle(getString(R.string.title_question))
        alertDialog.setMessage(getString(R.string.question))

        alertDialog.setPositiveButton(getString(R.string.yes)) { _, _ ->
            Toast.makeText(
                this,
                getString(R.string.cool),
                Toast.LENGTH_SHORT
            ).show()
        }

        alertDialog.setNegativeButton(getString(R.string.no)) { _, _ ->
            Toast.makeText(this, getString(R.string.sadly), Toast.LENGTH_SHORT).show()
        }

        alertDialog.show()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    companion object {

        private const val TAG = "SecondActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, SecondActivity::class.java)
        }
    }
}
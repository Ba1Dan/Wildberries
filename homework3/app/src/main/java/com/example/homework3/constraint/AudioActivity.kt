package com.example.homework3.constraint

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.R
import com.example.homework3.constraint.adapter.LogoAdapter
import com.google.android.material.button.MaterialButton

class AudioActivity : AppCompatActivity() {

    private lateinit var rvLogo: RecyclerView
    private lateinit var btnNextSong: MaterialButton
    private lateinit var btnPreviousSong: MaterialButton

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_activuty)

        supportActionBar?.hide()

        initView()

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        setAdapter()
        setClickListener()
    }

    private fun initView() {
        rvLogo = findViewById(R.id.rv_logo)
        btnNextSong = findViewById(R.id.btn_next_song)
        btnPreviousSong = findViewById(R.id.btn_previous_song)
    }

    private fun setClickListener() {
        btnNextSong.setOnClickListener {
            rvLogo.smoothScrollToPosition(linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1)
        }

        btnPreviousSong.setOnClickListener {
            rvLogo.smoothScrollToPosition(linearLayoutManager.findLastCompletelyVisibleItemPosition() - 1)
        }
    }

    private fun setAdapter() {
        val logoAdapter = LogoAdapter()
        rvLogo.adapter = logoAdapter
        rvLogo.layoutManager = linearLayoutManager
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvLogo)

        logoAdapter.setData(listOf("", "", "", ""))
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, AudioActivity::class.java)
        }
    }
}
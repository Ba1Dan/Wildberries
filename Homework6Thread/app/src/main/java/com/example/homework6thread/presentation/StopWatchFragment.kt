package com.example.homework6thread.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.homework6thread.databinding.FragmentStopWatchBinding
import com.example.homework6thread.di.GlobalDI
import com.example.homework6thread.domain.StateBackground
import java.util.*
import kotlin.random.Random


class StopWatchFragment : BaseFragment<FragmentStopWatchBinding>() {

    private var seconds = 0
    private var result = ""
    private var running = true

    private val viewModel: MainViewModel by lazy { GlobalDI.INSTANCE.mainViewModel }
    private lateinit var handler: Handler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlay.setOnClickListener {
            viewModel.start()
        }

        binding.btnPause.setOnClickListener {
            viewModel.pause()
        }

        binding.btnReset.setOnClickListener {
            viewModel.reset()
        }

        viewModel.time.observe(viewLifecycleOwner) { result ->
            binding.tvTime.text = result
        }

        viewModel.color.observe(viewLifecycleOwner) {state ->
            when(state) {
                is StateBackground.Reset -> {
                    binding.root.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.white))
                }
                is StateBackground.Resumed -> {

                }
                is StateBackground.Changed -> {
                    binding.root.setBackgroundColor(state.color)
                }
            }


        }

//        Thread{
//            Looper.prepare()
//            handler = Handler(Looper.myLooper()!!)
//            handler.postDelayed(timeUpdaterRunnable, 1000)
//            Looper.loop()
//        }.start()
    }

    private fun resetTime() {
        running = false
        seconds = 0
        result = ""
    }

    private fun pauseTime() {
        running = false
    }

    private fun startTime() {
        running = true
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStopWatchBinding = FragmentStopWatchBinding.inflate(inflater, container, false)

    private val timeUpdaterRunnable: Runnable = object : Runnable {
        override fun run() {
            val hours = seconds / 3600
            val minutes = seconds % 3600 / 60
            val secs = seconds % 60

            val time: String = String.format(
                Locale.getDefault(),
                "%d:%02d:%02d", hours,
                minutes, secs
            )

            activity?.runOnUiThread {
                binding.tvTime.text = time
            }

            if (seconds !=0 && running && seconds % 20 == 0) {
                val randomColor: Int = -Random.nextInt(255 * 255 * 255)
                activity?.runOnUiThread {
                    binding.root.setBackgroundColor(randomColor)
                }
            }

            //Обновляем время и число
            if (running) {
                seconds++
                result += Random.nextInt(0, 10)
            }

            handler.postDelayed(this, 1000)
        }
    }
}
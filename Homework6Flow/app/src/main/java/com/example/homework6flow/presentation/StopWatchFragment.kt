package com.example.homework6flow.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.homework6flow.di.GlobalDI
import com.example.homework6flow.domain.StateBackground
import com.example.homework6flow.databinding.FragmentStopWatchBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class StopWatchFragment : BaseFragment<FragmentStopWatchBinding>() {

    private val viewModel: MainViewModel by lazy { GlobalDI.INSTANCE.mainViewModel }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStopWatchBinding = FragmentStopWatchBinding.inflate(inflater, container, false)

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

        lifecycleScope.launch {
            viewModel.formattedTime.collect { time ->
                binding.tvTime.text = time
            }
        }

        lifecycleScope.launch {
            viewModel.changeColor.collect { stateBackground ->
                handleStateBackground(stateBackground)
            }
        }
    }

    private fun handleStateBackground(stateBackground: StateBackground) {
        when (stateBackground) {
            is StateBackground.Reset -> {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.white
                    )
                )
            }

            is StateBackground.Changed -> {
                binding.root.setBackgroundColor(stateBackground.color)
            }

            is StateBackground.Resumed -> {

            }
        }
    }
}
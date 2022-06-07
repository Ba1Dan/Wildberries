package com.example.homework6coroutines.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.homework6coroutines.domain.StateBackground
import com.example.homework6coroutines.databinding.FragmentStopWatchBinding
import com.example.homework6coroutines.di.GlobalDI


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

        viewModel.formattedTime.observe(viewLifecycleOwner) { time ->
            binding.tvTime.text = time
        }

        viewModel.changeColor.observe(viewLifecycleOwner) { stateBackground ->
            handleStateBackground(stateBackground)
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

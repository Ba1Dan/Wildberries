package com.example.homework6flow.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.homework6flow.di.GlobalDI
import com.example.homework6flow.databinding.FragmentNumberBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class NumberFragment : BaseFragment<FragmentNumberBinding>() {

    private val viewModel: MainViewModel by lazy { GlobalDI.INSTANCE.mainViewModel }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNumberBinding = FragmentNumberBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.number.collect { time ->
                binding.tvNumber.text = time
            }
        }
    }
}
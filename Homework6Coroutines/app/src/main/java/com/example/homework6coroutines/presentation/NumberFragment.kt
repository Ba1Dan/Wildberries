package com.example.homework6coroutines.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework6coroutines.databinding.FragmentNumberBinding
import com.example.homework6coroutines.di.GlobalDI
import com.example.homework6coroutines.presentation.BaseFragment
import com.example.homework6coroutines.presentation.MainViewModel

class NumberFragment : BaseFragment<FragmentNumberBinding>() {

    private val viewModel: MainViewModel by lazy { GlobalDI.INSTANCE.mainViewModel }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNumberBinding = FragmentNumberBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.number.observe(viewLifecycleOwner) { time ->
            binding.tvNumber.text = time
        }
    }
}
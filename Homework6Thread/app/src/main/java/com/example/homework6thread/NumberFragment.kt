package com.example.homework6thread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework6thread.databinding.FragmentNumberBinding


class NumberFragment : BaseFragment<FragmentNumberBinding>() {

    private val mainViewModel: MainViewModel by lazy { GlobalDI.INSTANCE.mainViewModel }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNumberBinding = FragmentNumberBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.number.observe(viewLifecycleOwner) { num ->
            binding.tvNumber.text = num
        }
    }

    companion object {

    }
}
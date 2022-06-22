package com.example.homework8cats.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.homework8cats.data.model.Cat
import com.example.homework8cats.databinding.FragmentMainBinding
import com.example.homework8cats.di.GlobalDI
import com.example.homework8cats.presentation.ui.base.BaseFragment
import com.example.homework8cats.presentation.util.State

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = GlobalDI.INSTANCE.mainViewModel
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCat()

        viewModel.cat.observe(viewLifecycleOwner) { state ->
            handleState(state)
        }

        binding.btnLike.setOnClickListener {
            viewModel.saveImageInFavourites()
            viewModel.getCat()
        }

        binding.btnUnlike.setOnClickListener {
            viewModel.getCat()
        }
    }

    private fun handleState(state: State<Cat>) {
        when (state) {
            is State.Result -> {
                binding.ivPicture.setImageURI(state.data.url)
            }

            is State.Loading -> {

            }

            is State.Error -> {
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
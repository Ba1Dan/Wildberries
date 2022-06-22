package com.example.homework8cats.presentation.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework8cats.data.model.FavouriteCat
import com.example.homework8cats.databinding.FragmentFavouriteBinding
import com.example.homework8cats.di.GlobalDI
import com.example.homework8cats.presentation.ui.base.BaseFragment
import com.example.homework8cats.presentation.util.State


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {

    private lateinit var viewModel: FavouriteViewModel
    private lateinit var catsAdapter: CatsAdapter

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavouriteBinding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = GlobalDI.INSTANCE.favouriteViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.getFavouriteCat()

        viewModel.cat.observe(viewLifecycleOwner) { state ->
            handleState(state)
        }
    }

    private fun initAdapter() {
        catsAdapter = CatsAdapter()
        binding.rvFavouriteCats.adapter = catsAdapter
        binding.rvFavouriteCats.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }

    private fun handleState(state: State<List<FavouriteCat>>) {
        when (state) {
            is State.Result -> {
                binding.progressBar.isVisible = false
                catsAdapter.cats = state.data
            }

            is State.Loading -> {
                binding.progressBar.isVisible = true
            }

            is State.Error -> {
                binding.progressBar.isVisible = false
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
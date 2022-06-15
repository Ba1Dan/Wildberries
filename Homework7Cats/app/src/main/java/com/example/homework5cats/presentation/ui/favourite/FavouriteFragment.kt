package com.example.homework5cats.presentation.ui.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework5cats.data.model.Cat
import com.example.homework5cats.data.model.FavouriteCat
import com.example.homework5cats.databinding.FragmentFavouriteBinding
import com.example.homework5cats.di.GlobalDI
import com.example.homework5cats.presentation.ui.base.BaseFragment
import com.example.homework5cats.presentation.util.State


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
            render(state)
        }
    }

    private fun initAdapter() {
        catsAdapter = CatsAdapter()
        binding.rvFavouriteCats.adapter = catsAdapter
        binding.rvFavouriteCats.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }

    private fun render(state: State<List<FavouriteCat>>) {
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
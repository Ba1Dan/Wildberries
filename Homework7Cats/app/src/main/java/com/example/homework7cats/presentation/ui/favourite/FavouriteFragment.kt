package com.example.homework7cats.presentation.ui.favourite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework7cats.core.App
import com.example.homework7cats.data.model.FavouriteCat
import com.example.homework7cats.databinding.FragmentFavouriteBinding
import com.example.homework7cats.presentation.ui.base.BaseFragment
import com.example.homework7cats.presentation.util.State
import com.example.homework7cats.presentation.util.ViewModelFactory
import javax.inject.Inject


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {

    private lateinit var viewModel: FavouriteViewModel
    private lateinit var catsAdapter: CatsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavouriteViewModel::class.java]
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavouriteBinding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)

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
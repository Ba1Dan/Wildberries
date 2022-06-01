package com.example.homeworkdota.presentation.ui.list

import BaseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkdota.R
import com.example.homeworkdota.data.model.Hero
import com.example.homeworkdota.databinding.FragmentHeroesBinding
import com.example.homeworkdota.di.GlobalDI
import com.example.homeworkdota.presentation.ui.detail.DetailFragment
import com.example.homeworkdota.presentation.util.State


class HeroesFragment : BaseFragment<FragmentHeroesBinding>(), OnHeroClickListener {

    private lateinit var heroesAdapter: HeroesAdapter
    private val viewModel by lazy { GlobalDI.INSTANCE.heroesViewModel }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHeroesBinding = FragmentHeroesBinding.inflate(inflater, container, false)

    override fun onClick(hero: Hero) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_fragment_container, DetailFragment.newInstance(hero))
            .commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.getHeroes()

        viewModel.heroes.observe(viewLifecycleOwner) { state ->
            render(state)
        }
    }

    private fun render(state: State<List<Hero>>) {
        when (state) {
            is State.Result -> {
                binding.progressBar.isVisible = false
                heroesAdapter.heroes = state.data
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

    private fun initAdapter() {
        heroesAdapter = HeroesAdapter(this)
        binding.rvHeroes.adapter = heroesAdapter
        binding.rvHeroes.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {

        fun newInstance() = HeroesFragment()
    }
}
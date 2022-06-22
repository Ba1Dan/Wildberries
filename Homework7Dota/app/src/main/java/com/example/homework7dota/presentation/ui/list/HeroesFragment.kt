package com.example.homework7dota.presentation.ui.list

import com.example.homework7dota.presentation.ui.base.BaseFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework7dota.presentation.util.ViewModelFactory
import com.example.homework7dota.R
import com.example.homework7dota.core.App
import com.example.homework7dota.data.model.Hero
import com.example.homework7dota.databinding.FragmentHeroesBinding
import com.example.homework7dota.presentation.ui.detail.DetailFragment
import com.example.homework7dota.presentation.util.State
import javax.inject.Inject


class HeroesFragment : BaseFragment<FragmentHeroesBinding>(), OnHeroClickListener {

    private lateinit var heroesAdapter: HeroesAdapter
    private lateinit var viewModel: HeroesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HeroesViewModel::class.java]
    }

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
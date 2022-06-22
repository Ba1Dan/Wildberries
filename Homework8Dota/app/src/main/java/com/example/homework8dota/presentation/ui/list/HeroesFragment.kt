package com.example.homework8dota.presentation.ui.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework8dota.R
import com.example.homework8dota.data.model.Hero
import com.example.homework8dota.databinding.FragmentHeroesBinding
import com.example.homework8dota.di.GlobalDI
import com.example.homework8dota.presentation.ui.base.BaseFragment
import com.example.homework8dota.presentation.ui.detail.DetailFragment
import com.example.homework8dota.presentation.ui.info.InfoFragment
import com.example.homework8dota.presentation.util.State


class HeroesFragment : BaseFragment<FragmentHeroesBinding>(), OnHeroClickListener {

    private lateinit var heroesAdapter: HeroesAdapter
    private val viewModel by lazy { GlobalDI.INSTANCE.heroesViewModel }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHeroesBinding {
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return FragmentHeroesBinding.inflate(inflater, container, false)
    }


    override fun onClick(hero: Hero) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_fragment_container, DetailFragment.newInstance(hero))
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {

                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.main_fragment_container, InfoFragment.newInstance())
                    .commit()

                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
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
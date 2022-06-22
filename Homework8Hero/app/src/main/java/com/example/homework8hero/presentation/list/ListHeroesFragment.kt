package com.example.homework8hero.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework8dota.presentation.ui.base.BaseFragment
import com.example.homework8hero.core.App
import com.example.homework8hero.data.model.Hero
import com.example.homework8hero.databinding.FragmentListHeroesBinding
import com.example.homework8hero.presentation.list.adapter.HeroesAdapter
import com.example.homework8hero.presentation.list.adapter.OnHeroClickListener
import com.example.homework8hero.presentation.util.Screens.DetailHero
import com.example.homework8hero.presentation.util.State
import com.example.homework8hero.presentation.util.ViewModelFactory
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class ListHeroesFragment : BaseFragment<FragmentListHeroesBinding>(), OnHeroClickListener {

    private lateinit var adapter: HeroesAdapter
    private lateinit var viewModel: ListHeroesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var router: Router

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ListHeroesViewModel::class.java]
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListHeroesBinding = FragmentListHeroesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HeroesAdapter(this)
        binding.rvHeroes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHeroes.adapter = adapter

        viewModel.searchHeroes("batman")

        viewModel.heroes.observe(viewLifecycleOwner) { state ->
            render(state)
        }
    }

    override fun onClick(id: Int) {
        router.navigateTo(DetailHero(id))
    }

    private fun render(state: State<List<Hero>>) {
        when (state) {
            is State.Result -> {
                binding.progressBar.isVisible = false
                adapter.heroes = state.data
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

    companion object {

        fun newInstance() = ListHeroesFragment()
    }
}
package com.example.homework5hero.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework5hero.R
import com.example.homework5hero.data.model.Hero
import com.example.homework5hero.databinding.FragmentListHeroesBinding
import com.example.homework5hero.di.GlobalDI
import com.example.homework5hero.presentation.State
import com.example.homework5hero.presentation.detail.DetailHeroFragment
import com.example.homework5hero.presentation.list.adapter.HeroesAdapter
import com.example.homework5hero.presentation.list.adapter.OnHeroClickListener

class ListHeroesFragment : Fragment(), OnHeroClickListener {

    private var _binding: FragmentListHeroesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListHeroesViewModel = GlobalDI.INSTANCE.listHeroesViewModel

    private lateinit var adapter: HeroesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListHeroesBinding.inflate(inflater, container, false)

        return binding.root
    }

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
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_fragment_container, DetailHeroFragment.newInstance(id))
            .commit()
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
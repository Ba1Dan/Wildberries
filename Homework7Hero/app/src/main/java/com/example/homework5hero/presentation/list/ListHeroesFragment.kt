package com.example.homework5hero.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework5hero.R
import com.example.homework5hero.databinding.FragmentListHeroesBinding
import com.example.homework5hero.di.GlobalDI
import com.example.homework5hero.presentation.list.adapter.HeroesAdapter
import com.example.homework5hero.presentation.list.adapter.OnHeroClickListener
import com.example.homework5hero.presentation.detail.DetailHeroFragment

class ListHeroesFragment : Fragment(), OnHeroClickListener {

    private var _binding: FragmentListHeroesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListHeroesViewModel = GlobalDI.INSTANCE.listHeroesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListHeroesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HeroesAdapter(this)
        binding.rvHeroes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHeroes.adapter = adapter

        viewModel.searchHeroes("batman")

        viewModel.heroes.observe(viewLifecycleOwner) {
            adapter.setDataSource(it)
        }
    }

    override fun onClick(id: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_fragment_container, DetailHeroFragment.newInstance(id))
            .commit()
    }

    companion object {

        fun newInstance() = ListHeroesFragment()
    }
}
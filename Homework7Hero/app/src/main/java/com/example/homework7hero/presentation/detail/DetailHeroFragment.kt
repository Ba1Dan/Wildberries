package com.example.homework7hero.presentation.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.homework7hero.core.App
import com.example.homework7hero.data.model.HeroDetail
import com.example.homework7hero.databinding.FragmentDetailHeroBinding
import com.example.homework7hero.presentation.base.BaseFragment
import com.example.homework7hero.presentation.util.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailHeroFragment : BaseFragment<FragmentDetailHeroBinding>() {

    private val heroID: Int by lazy { arguments?.getInt(ARG_ID_HERO)!! }
    private lateinit var viewModel: DetailHeroViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailHeroViewModel::class.java]
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailHeroBinding = FragmentDetailHeroBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHeroById(heroID)

        viewModel.hero.observe(viewLifecycleOwner) { hero ->
            handleResult(hero)
        }
    }

    private fun handleResult(heroDetail: HeroDetail) {
        binding.tvAliases.text = heroDetail.biography.aliases.joinToString(",")
        binding.tvFullName.text = heroDetail.name
        binding.tvPlaceOfBirth.text = heroDetail.biography.placeOfBirth
        binding.tvPublisher.text = heroDetail.biography.publisher
        Picasso.with(binding.root.context)
            .load(heroDetail.image.url)
            .into(binding.ivLogo)
    }

    companion object {

        private const val ARG_ID_HERO = "arg_id_hero"

        fun newInstance(id: Int) =
            DetailHeroFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID_HERO, id)
                }
            }
    }
}
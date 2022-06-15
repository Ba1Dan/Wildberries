package com.example.homework5hero.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework5hero.R
import com.example.homework5hero.data.model.HeroDetail
import com.example.homework5hero.databinding.FragmentDetailHeroBinding
import com.example.homework5hero.di.GlobalDI
import com.squareup.picasso.Picasso

class DetailHeroFragment : Fragment() {

    private var _binding: FragmentDetailHeroBinding? = null
    private val binding get() = _binding!!

    private val heroID: Int by lazy { arguments?.getInt(ARG_ID_HERO)!! }
    private val viewModel: DetailHeroViewModel by lazy { DetailHeroViewModel(
            getInfoHero = GlobalDI.INSTANCE.getInfoHero
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

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
//                .placeholder(R.drawable.user_placeholder)
//                .error(R.drawable.user_placeholder_error)
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
package com.example.homeworkdota.presentation.ui.detail

import BaseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.size.Scale
import com.example.homeworkdota.R
import com.example.homeworkdota.data.model.Hero
import com.example.homeworkdota.data.network.RemoteDataSource.Companion.BASE_URL
import com.example.homeworkdota.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val hero: Hero by lazy { arguments?.getParcelable(ARG_PARAM_HERO)!! }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        binding.tvName.text = hero.name
        binding.tvAttackType.text = hero.attackType
        binding.tvRoles.text = hero.roles.joinToString(",")
        binding.ivPicture.load(BASE_URL + hero.image)
    }

    companion object {

        private const val ARG_PARAM_HERO = "param_hero"

        fun newInstance(hero: Hero) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM_HERO, hero)
                }
            }
    }
}
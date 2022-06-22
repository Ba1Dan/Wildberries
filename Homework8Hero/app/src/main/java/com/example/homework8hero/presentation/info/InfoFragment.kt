package com.example.homework8hero.presentation.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework8hero.databinding.FragmentInfoBinding
import com.example.homework8dota.presentation.ui.base.BaseFragment
import com.example.homework8hero.core.App
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class InfoFragment : BaseFragment<FragmentInfoBinding>() {

    @Inject
    lateinit var router: Router

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentInfoBinding =
        FragmentInfoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            router.exit()
        }
    }

    companion object {

        fun newInstance() = InfoFragment()
    }
}
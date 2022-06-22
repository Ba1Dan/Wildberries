package com.example.homework8cats.presentation.ui.info

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework8cats.databinding.FragmentInfoBinding
import com.example.homework8cats.presentation.ui.base.BaseFragment

class InfoFragment : BaseFragment<FragmentInfoBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentInfoBinding =
        FragmentInfoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvGitHub.movementMethod = LinkMovementMethod.getInstance()
    }

    companion object {

        fun newInstance() = InfoFragment()
    }
}
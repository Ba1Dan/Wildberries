package com.example.homework8cats.presentation.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework8cats.R
import com.example.homework8cats.databinding.FragmentInfoBinding
import com.example.homework8cats.presentation.ui.base.BaseFragment

class InfoFragment : BaseFragment<FragmentInfoBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentInfoBinding =
        FragmentInfoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            InfoFragment()
    }
}
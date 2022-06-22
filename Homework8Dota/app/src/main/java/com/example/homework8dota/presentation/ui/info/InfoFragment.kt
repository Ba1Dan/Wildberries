package com.example.homework8dota.presentation.ui.info

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework8dota.databinding.FragmentInfoBinding
import com.example.homework8dota.presentation.ui.base.BaseFragment

class InfoFragment : BaseFragment<FragmentInfoBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentInfoBinding =
        FragmentInfoBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvGitHub.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().supportFragmentManager.popBackStack()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun newInstance() = InfoFragment()
    }
}
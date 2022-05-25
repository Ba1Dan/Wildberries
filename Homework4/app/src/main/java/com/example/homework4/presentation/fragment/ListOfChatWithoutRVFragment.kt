package com.example.homework4.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework4.databinding.FragmentListOfChatWithoutRVBinding
import com.example.homework4.databinding.ItemChatBinding
import com.example.homework4.repository.Repository


class ListOfChatWithoutRVFragment : Fragment() {

    private var _binding: FragmentListOfChatWithoutRVBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfChatWithoutRVBinding.inflate(inflater, container, false)
        repository = Repository()

        val data = repository.getChats(10)

        for (item in data) {
            val itemBinding = ItemChatBinding.inflate(layoutInflater, binding.container, false)

            itemBinding.tvTitleChat.text = item.title
            itemBinding.tvCountUnreadMessages.text = item.countOfUnread.toString()

            binding.container.addView(itemBinding.root)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        fun newInstance() =
            ListOfChatWithoutRVFragment()
    }
}
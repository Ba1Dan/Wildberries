package com.example.homework4.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework4.R
import com.example.homework4.adapter.ChatAdapter
import com.example.homework4.adapter.OnClickListenerChat
import com.example.homework4.adapter.SwipeToDeleteCallback
import com.example.homework4.databinding.FragmentListOfChatBinding
import com.example.homework4.model.Chat
import com.example.homework4.presentation.dialog.CreateChatDialog
import com.example.homework4.presentation.dialog.CreateChatDialog.Companion.CREATE_CHAT_REQUEST_KEY
import com.example.homework4.presentation.dialog.CreateChatDialog.Companion.TITLE_RESULT_KEY
import com.example.homework4.repository.Repository

class ListOfChatFragment : Fragment(), OnClickListenerChat {

    private var _binding: FragmentListOfChatBinding? = null
    private val binding get() = _binding!!

    private lateinit var chatAdapter: ChatAdapter
    private lateinit var repository: Repository

    private var countOfPaging: Int = 1
    private var isLoadNewPage = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(CREATE_CHAT_REQUEST_KEY) { _, bundle ->
            val chatName = bundle.getString(TITLE_RESULT_KEY) as String
            repository.addChat(Chat(repository.getIndexChat(), chatName, "", 0, mutableListOf()))
            updateChats()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfChatBinding.inflate(inflater, container, false)
        repository = Repository()
        chatAdapter = ChatAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()
        setListeners()
    }

    override fun onClick(chatId: Int, chatTitle: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_fragment_container, ChatFragment.newInstance(chatId, chatTitle))
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.btnAddChat.setOnClickListener {
            CreateChatDialog.newInstance().show(parentFragmentManager, null)
        }

        binding.swipeContainer.setOnRefreshListener {
            updateChats()
        }
    }

    private fun setupRecyclerView() {
        binding.rvChats.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvChats.adapter = chatAdapter

        chatAdapter.chats = repository.getChats(countOfPaging++)

        setItemDecoration()
        setTouchHelper()
        addOnScrollListener()
    }

    private fun setItemDecoration() {
        binding.rvChats.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            ).apply {
                setDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.shape_line
                    )!!
                )
            })
    }

    private fun addOnScrollListener() {
        binding.rvChats.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val position: Int =
                        (binding.rvChats.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (position <= REMAINDER && isLoadNewPage) {
                        isLoadNewPage = false
                        loadNextChats()
                    }
                }
            }
        })
    }

    private fun setTouchHelper() {
        val swipeToDeleteCallback = SwipeToDeleteCallback(chatAdapter) { position ->
            repository.deleteChat(position)
            updateChats()
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvChats)
    }

    private fun loadNextChats() {
        val data = repository.getChats(countOfPaging++)
        chatAdapter.chats = data
        isLoadNewPage = true
    }

    private fun updateChats() {
        chatAdapter.chats = repository.getChats(countOfPaging)
        binding.swipeContainer.isRefreshing = false
    }

    companion object {

        private const val REMAINDER = 5

        fun newInstance() = ListOfChatFragment()
    }
}
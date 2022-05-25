package com.example.homework4.presentation.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.homework4.R
import com.example.homework4.adapter.MessageAdapter
import com.example.homework4.adapter.OnClickListenerMessage
import com.example.homework4.databinding.FragmentChatBinding
import com.example.homework4.model.Message
import com.example.homework4.model.MessageType
import com.example.homework4.presentation.TypeScroll
import com.example.homework4.repository.Repository


class ChatFragment : Fragment(), OnClickListenerMessage {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: Repository
    private lateinit var messageAdapter: MessageAdapter

    private val chatId: Int by lazy { arguments?.getInt(ARG_CHAT_ID)!! }
    private val chatTitle: String by lazy { arguments?.getString(ARG_CHAT_TITLE)!! }

    private var countOfPaging: Int = 1
    private var positionRecyclerView: TypeScroll = TypeScroll.DOWN
    private var isLoadNewPage = true
    private var messageId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(ActionDialog.TYPE_CLICK_REQUEST_KEY) { _, bundle ->
            val click = bundle.getParcelable<TypeClick>(ActionDialog.TYPE_CLICK_RESULT_KEY)
            handleClick(click)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        messageAdapter = MessageAdapter(this)
        repository = Repository()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        binding.toolbarChat.title = chatTitle
        setClickListener()
    }

    private fun setClickListener() {
        binding.toolbarChat.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.btnSend.setOnClickListener {
            positionRecyclerView = TypeScroll.DOWN
            sendMessage()
            updateChat()
        }

        binding.btnCheck.setOnClickListener {
            positionRecyclerView = TypeScroll.DOWN
            editMessage()
            updateChat()
            binding.btnCheck.visibility = View.GONE
        }

        binding.inputMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.toString().isEmpty()) {
                        binding.btnAttachFile.visibility = View.VISIBLE
                        binding.btnMic.visibility = View.VISIBLE
                        binding.btnSend.visibility = View.GONE
                    } else {
                        binding.btnSend.visibility = View.VISIBLE
                        binding.btnMic.visibility = View.GONE
                        binding.btnAttachFile.visibility = View.GONE
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    override fun onClick(messageId: Int, message: String) {
        ActionDialog.newInstance(messageId, message).show(parentFragmentManager, null)
    }

    private fun editMessage() {
        messageId?.let { repository.editMessage(chatId, it, binding.inputMessage.text.toString()) }
        binding.inputMessage.setText("")
    }

    private fun setupRecyclerView() {
        binding.rvChat.adapter = messageAdapter
        val data = repository.getMessages(chatId, countOfPaging++)

        messageAdapter.messages = data
        binding.rvChat.smoothScrollToPosition(data.size)
        (binding.rvChat.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        binding.rvChat.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val position: Int =
                        (binding.rvChat.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    positionRecyclerView = TypeScroll.TOP

                    if (position <= REMAINDER && isLoadNewPage) {
                        isLoadNewPage = false
                        loadNextMessages()
                    }
                }
            }
        })
    }

    private fun loadNextMessages() {
        val data = repository.getMessages(chatId, countOfPaging++)
        messageAdapter.messages = data
        isLoadNewPage = true
    }

    private fun updateChat() {
        val data = repository.getMessages(chatId, countOfPaging)
        messageAdapter.messages = data
        binding.rvChat.smoothScrollToPosition(data.size)
    }

    private fun sendMessage() {
        repository.addMessage(
            chatId,
            Message(
                repository.getIndexMessage(chatId),
                binding.inputMessage.text.toString(),
                "Данияр",
                MessageType.OUT
            )
        )
        binding.inputMessage.setText("")
    }

    private fun copyMessage(message: String) {
        val clipBoard = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(null, message)
        clipBoard.setPrimaryClip(clip)

        Toast.makeText(
            requireContext(),
            getString(R.string.copied_to_clipboard),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun handleClick(click: TypeClick?) {
        when (click) {
            is TypeClick.EditMessage -> {

                binding.btnAttachFile.visibility = View.GONE
                binding.btnMic.visibility = View.GONE
                binding.btnSend.visibility = View.GONE
                binding.btnCheck.visibility = View.VISIBLE
                binding.inputMessage.setText(click.message)
                messageId = click.messageId
            }
            is TypeClick.DeleteMessage -> {
                repository.deleteMessage(chatId, click.messageId)
                updateChat()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.deleted_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
            is TypeClick.Copy -> {
                copyMessage(click.message)
            }
            else -> {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val ARG_CHAT_ID = "chat_id"
        private const val ARG_CHAT_TITLE = "chat_title"
        private const val REMAINDER = 5

        fun newInstance(chatId: Int, chatTitle: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CHAT_ID, chatId)
                    putString(ARG_CHAT_TITLE, chatTitle)
                }
            }
    }
}
package com.example.homework4.presentation.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.homework4.R

class CreateChatDialog : DialogFragment() {

    private lateinit var inputTitle: EditText
    private lateinit var btnCreate: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.create_chat_dialog, container, false)

        inputTitle = view.findViewById(R.id.input_title)
        btnCreate = view.findViewById(R.id.btn_create_stream)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCreate.setOnClickListener {
            setFragmentResult(
                CREATE_CHAT_REQUEST_KEY,
                bundleOf(
                    TITLE_RESULT_KEY to inputTitle.text.toString(),
                )
            )
            dismiss()
        }
    }

    companion object {

        const val CREATE_CHAT_REQUEST_KEY = "create_chat_request"
        const val TITLE_RESULT_KEY = "title_chat"

        fun newInstance() = CreateChatDialog()
    }
}
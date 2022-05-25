package com.example.homework4.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.homework4.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ActionDialog : BottomSheetDialogFragment() {

    private lateinit var btnEditMessage: TextView
    private lateinit var btnDelete: TextView
    private lateinit var btnCopy: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.action_dialog, container, false)

        btnEditMessage = view.findViewById(R.id.btn_edit)
        btnDelete = view.findViewById(R.id.btn_delete)
        btnCopy = view.findViewById(R.id.btn_copy)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val messageId: Int = requireArguments().getInt(ARGUMENT_ID)
        val message: String = requireArguments().getString(ARGUMENT_MESSAGE) ?: ""

        btnEditMessage.setOnClickListener {
            setFragmentResult(
                TYPE_CLICK_REQUEST_KEY,
                bundleOf(
                    TYPE_CLICK_RESULT_KEY to TypeClick.EditMessage(messageId, message),
                )
            )
            dismiss()
        }
        btnDelete.setOnClickListener {
            setFragmentResult(
                TYPE_CLICK_REQUEST_KEY,
                bundleOf(
                    TYPE_CLICK_RESULT_KEY to TypeClick.DeleteMessage(messageId),
                )
            )
            dismiss()
        }
        btnCopy.setOnClickListener {
            setFragmentResult(
                TYPE_CLICK_REQUEST_KEY,
                bundleOf(
                    TYPE_CLICK_RESULT_KEY to TypeClick.Copy(messageId, message),
                )
            )
            dismiss()
        }
    }

    companion object {

        private const val ARGUMENT_MESSAGE = "message"
        private const val ARGUMENT_ID = "message_id"
        const val TYPE_CLICK_RESULT_KEY = "message_id"
        const val TYPE_CLICK_REQUEST_KEY = "message_id"

        fun newInstance(messageId: Int, message: String) = ActionDialog().apply {
            val bundle = Bundle()
            bundle.putInt(ARGUMENT_ID, messageId)
            bundle.putString(ARGUMENT_MESSAGE, message)
            arguments = bundle
        }
    }
}
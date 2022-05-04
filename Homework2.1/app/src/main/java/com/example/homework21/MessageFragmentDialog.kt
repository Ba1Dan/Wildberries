package com.example.homework21

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MessageFragmentDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.setTitle(getString(R.string.title_question))
        alertDialog.setMessage(getString(R.string.question))

        alertDialog.setPositiveButton(getString(R.string.yes)) { _, _ ->
            Toast.makeText(
                requireContext(),
                getString(R.string.cool),
                Toast.LENGTH_SHORT
            ).show()
        }

        alertDialog.setNegativeButton(getString(R.string.no)) { _, _ ->
            Toast.makeText(requireContext(), getString(R.string.sadly), Toast.LENGTH_SHORT).show()
        }

        return alertDialog.create()
    }

    companion object {

        fun newInstance() = MessageFragmentDialog()
    }
}
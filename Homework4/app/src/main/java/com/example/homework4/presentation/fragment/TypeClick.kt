package com.example.homework4.presentation.fragment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface TypeClick: Parcelable {
    @Parcelize
    class EditMessage(val messageId: Int, val message: String) : TypeClick

    @Parcelize
    class DeleteMessage(val messageId: Int) : TypeClick

    @Parcelize
    class Copy(val messageId: Int, val message: String) : TypeClick
}
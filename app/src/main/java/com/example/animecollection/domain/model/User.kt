package com.example.animecollection.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val username: String = "",
    val email: String = "",
    val photo: String = ""
) : Parcelable

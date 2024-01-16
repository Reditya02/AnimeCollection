package com.example.animecollection.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val id: String,
    val posterImage: String,
    val coverImage: String,
    val titleEn: String,
    val titleJp: String,
    val rating: String,
    val synopsis: String,
    val genre: List<String>
) : Parcelable

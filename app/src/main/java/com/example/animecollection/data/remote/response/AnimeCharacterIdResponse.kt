package com.example.animecollection.data.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeCharacterIdResponse(

	@field:SerializedName("data")
	val data: List<CharacterIdDataItem>,

	@field:SerializedName("links")
	val links: Links
)

data class CharacterIdDataItem(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("type")
	val type: String
)

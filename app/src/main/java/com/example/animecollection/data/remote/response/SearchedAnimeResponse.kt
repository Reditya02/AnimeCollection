package com.example.animecollection.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchedAnimeResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("meta")
	val meta: SearchedMeta,

	@field:SerializedName("links")
	val links: SearchedLinks
)


data class SearchedLinks(

	@field:SerializedName("last")
	val last: String,

	@field:SerializedName("first")
	val first: String,

	@field:SerializedName("self")
	val self: String,

	@field:SerializedName("related")
	val related: String
)

data class SearchedMeta(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("dimensions")
	val dimensions: Dimensions
)

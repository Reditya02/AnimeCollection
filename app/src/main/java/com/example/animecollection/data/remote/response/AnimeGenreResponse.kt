package com.example.animecollection.data.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeGenreResponse(

	@field:SerializedName("data")
	val data: List<GenreDataItem>,

	@field:SerializedName("meta")
	val meta: GenreMeta,

	@field:SerializedName("links")
	val links: GenreLinks
)

data class GenreAttributes(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("slug")
	val slug: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class GenreDataItem(

	@field:SerializedName("links")
	val links: Links,

	@field:SerializedName("attributes")
	val attributes: GenreAttributes,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("type")
	val type: String
)

data class GenreMeta(

	@field:SerializedName("count")
	val count: Int
)

data class GenreLinks(

	@field:SerializedName("last")
	val last: String,

	@field:SerializedName("first")
	val first: String,

	@field:SerializedName("self")
	val self: String
)

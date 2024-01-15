package com.example.animecollection.data.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeCharacterResponse(

	@field:SerializedName("data")
	val data: CharacterData
)

data class PrimaryMedia(

	@field:SerializedName("links")
	val links: Links
)

data class MediaCharacters(

	@field:SerializedName("links")
	val links: Links
)

data class CharacterAttributes(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("image")
	val image: Image,

	@field:SerializedName("names")
	val names: Names,

	@field:SerializedName("otherNames")
	val otherNames: List<Any>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("slug")
	val slug: String,

	@field:SerializedName("malId")
	val malId: Any,

	@field:SerializedName("updatedAt")
	val updatedAt: String,

	@field:SerializedName("canonicalName")
	val canonicalName: String
)

data class CharacterData(

	@field:SerializedName("relationships")
	val relationships: CharacterRelationships,

	@field:SerializedName("links")
	val links: Links,

	@field:SerializedName("attributes")
	val attributes: CharacterAttributes,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("type")
	val type: String
)

data class Names(

	@field:SerializedName("en")
	val en: String
)

data class CharacterRelationships(

	@field:SerializedName("castings")
	val castings: Castings,

	@field:SerializedName("primaryMedia")
	val primaryMedia: PrimaryMedia,

	@field:SerializedName("mediaCharacters")
	val mediaCharacters: MediaCharacters,

	@field:SerializedName("quotes")
	val quotes: Quotes
)

data class Image(

	@field:SerializedName("small")
	val small: String,

	@field:SerializedName("original")
	val original: String,

	@field:SerializedName("large")
	val large: String,

	@field:SerializedName("tiny")
	val tiny: String,

	@field:SerializedName("meta")
	val meta: Meta,

	@field:SerializedName("medium")
	val medium: String
)

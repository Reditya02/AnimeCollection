package com.example.animecollection.data.remote.api.response

import com.google.gson.annotations.SerializedName

data class Data(

    @field:SerializedName("relationships")
	val relationships: Relationships,

    @field:SerializedName("links")
	val links: Links,

    @field:SerializedName("attributes")
	val attributes: Attributes,

    @field:SerializedName("id")
	val id: String,

    @field:SerializedName("type")
	val type: String
)
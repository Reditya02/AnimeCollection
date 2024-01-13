package com.example.animecollection.data.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeResponse(

	@field:SerializedName("data")
	val data: List<DataItem>
)

data class Staff(

	@field:SerializedName("links")
	val links: Links
)

data class AnimeProductions(

	@field:SerializedName("links")
	val links: Links
)

data class Large(

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("height")
	val height: Int
)

data class Productions(

	@field:SerializedName("links")
	val links: Links
)

data class DataItem(

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

data class Relationships(

	@field:SerializedName("animeCharacters")
	val animeCharacters: AnimeCharacters,

	@field:SerializedName("animeProductions")
	val animeProductions: AnimeProductions,

	@field:SerializedName("staff")
	val staff: Staff,

	@field:SerializedName("streamingLinks")
	val streamingLinks: StreamingLinks,

	@field:SerializedName("quotes")
	val quotes: Quotes,

	@field:SerializedName("characters")
	val characters: Characters,

	@field:SerializedName("castings")
	val castings: Castings,

	@field:SerializedName("mappings")
	val mappings: Mappings,

	@field:SerializedName("animeStaff")
	val animeStaff: AnimeStaff,

	@field:SerializedName("reviews")
	val reviews: Reviews,

	@field:SerializedName("installments")
	val installments: Installments,

	@field:SerializedName("genres")
	val genres: Genres,

	@field:SerializedName("mediaRelationships")
	val mediaRelationships: MediaRelationships,

	@field:SerializedName("categories")
	val categories: Categories,

	@field:SerializedName("productions")
	val productions: Productions,

	@field:SerializedName("episodes")
	val episodes: Episodes
)

data class Castings(

	@field:SerializedName("links")
	val links: Links
)

data class RatingFrequencies(

	@field:SerializedName("11")
	val jsonMember11: String,

	@field:SerializedName("12")
	val jsonMember12: String,

	@field:SerializedName("13")
	val jsonMember13: String,

	@field:SerializedName("14")
	val jsonMember14: String,

	@field:SerializedName("15")
	val jsonMember15: String,

	@field:SerializedName("16")
	val jsonMember16: String,

	@field:SerializedName("17")
	val jsonMember17: String,

	@field:SerializedName("18")
	val jsonMember18: String,

	@field:SerializedName("19")
	val jsonMember19: String,

	@field:SerializedName("2")
	val jsonMember2: String,

	@field:SerializedName("3")
	val jsonMember3: String,

	@field:SerializedName("4")
	val jsonMember4: String,

	@field:SerializedName("5")
	val jsonMember5: String,

	@field:SerializedName("6")
	val jsonMember6: String,

	@field:SerializedName("7")
	val jsonMember7: String,

	@field:SerializedName("8")
	val jsonMember8: String,

	@field:SerializedName("9")
	val jsonMember9: String,

	@field:SerializedName("20")
	val jsonMember20: String,

	@field:SerializedName("10")
	val jsonMember10: String
)

data class Medium(

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("height")
	val height: Int
)

data class StreamingLinks(

	@field:SerializedName("links")
	val links: Links
)

data class Characters(

	@field:SerializedName("links")
	val links: Links
)

data class Genres(

	@field:SerializedName("links")
	val links: Links
)

data class PosterImage(

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

data class Meta(

	@field:SerializedName("dimensions")
	val dimensions: Dimensions
)

data class Attributes(

	@field:SerializedName("nextRelease")
	val nextRelease: Any,

	@field:SerializedName("endDate")
	val endDate: String,

	@field:SerializedName("episodeCount")
	val episodeCount: Int,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("ratingRank")
	val ratingRank: Int,

	@field:SerializedName("posterImage")
	val posterImage: PosterImage,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("subtype")
	val subtype: String,

	@field:SerializedName("youtubeVideoId")
	val youtubeVideoId: String,

	@field:SerializedName("averageRating")
	val averageRating: String,

	@field:SerializedName("coverImage")
	val coverImage: CoverImage,

	@field:SerializedName("ratingFrequencies")
	val ratingFrequencies: RatingFrequencies,

	@field:SerializedName("showType")
	val showType: String,

	@field:SerializedName("abbreviatedTitles")
	val abbreviatedTitles: List<String>,

	@field:SerializedName("slug")
	val slug: String,

	@field:SerializedName("episodeLength")
	val episodeLength: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String,

	@field:SerializedName("nsfw")
	val nsfw: Boolean,

	@field:SerializedName("synopsis")
	val synopsis: String,

	@field:SerializedName("titles")
	val titles: Titles,

	@field:SerializedName("ageRating")
	val ageRating: String,

	@field:SerializedName("totalLength")
	val totalLength: Int,

	@field:SerializedName("favoritesCount")
	val favoritesCount: Int,

	@field:SerializedName("coverImageTopOffset")
	val coverImageTopOffset: Int,

	@field:SerializedName("canonicalTitle")
	val canonicalTitle: String,

	@field:SerializedName("tba")
	val tba: Any,

	@field:SerializedName("userCount")
	val userCount: Int,

	@field:SerializedName("popularityRank")
	val popularityRank: Int,

	@field:SerializedName("ageRatingGuide")
	val ageRatingGuide: String,

	@field:SerializedName("startDate")
	val startDate: String,

	@field:SerializedName("status")
	val status: String
)

data class Tiny(

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("height")
	val height: Int
)

data class Titles(

	@field:SerializedName("en")
	val en: String,

	@field:SerializedName("ja_jp")
	val jaJp: String,

	@field:SerializedName("en_jp")
	val enJp: String,

	@field:SerializedName("en_us")
	val enUs: String,

	@field:SerializedName("it_it")
	val itIt: String,

	@field:SerializedName("ko_kr")
	val koKr: String,

	@field:SerializedName("zh_cn")
	val zhCn: String,

	@field:SerializedName("th_th")
	val thTh: String
)

data class AnimeCharacters(

	@field:SerializedName("links")
	val links: Links
)

data class Links(

	@field:SerializedName("self")
	val self: String,

	@field:SerializedName("related")
	val related: String
)

data class Categories(

	@field:SerializedName("links")
	val links: Links
)

data class Reviews(

	@field:SerializedName("links")
	val links: Links
)

data class Dimensions(

	@field:SerializedName("small")
	val small: Small,

	@field:SerializedName("large")
	val large: Large,

	@field:SerializedName("tiny")
	val tiny: Tiny,

	@field:SerializedName("medium")
	val medium: Medium
)

data class Quotes(

	@field:SerializedName("links")
	val links: Links
)

data class Episodes(

	@field:SerializedName("links")
	val links: Links
)

data class AnimeStaff(

	@field:SerializedName("links")
	val links: Links
)

data class Installments(

	@field:SerializedName("links")
	val links: Links
)

data class CoverImage(

	@field:SerializedName("small")
	val small: String,

	@field:SerializedName("original")
	val original: String,

	@field:SerializedName("large")
	val large: String,

	@field:SerializedName("tiny")
	val tiny: String,

	@field:SerializedName("meta")
	val meta: Meta
)

data class Mappings(

	@field:SerializedName("links")
	val links: Links
)

data class MediaRelationships(

	@field:SerializedName("links")
	val links: Links
)

data class Small(

	@field:SerializedName("width")
	val width: Int,

	@field:SerializedName("height")
	val height: Int
)

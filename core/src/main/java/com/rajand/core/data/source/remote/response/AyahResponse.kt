package com.rajand.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AyahResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("data")
	val data: Data
)

data class Data(

	@field:SerializedName("number")
	val number: Int,

	@field:SerializedName("verses")
	val verses: List<VersesItem>
)

data class VersesItem(

	@field:SerializedName("number")
	val number: Number,

	@field:SerializedName("translation")
	val translation: TranslationAyah,

	@field:SerializedName("text")
	val text: Text,

	@field:SerializedName("audio")
	val audio: Audio
)

data class Number(

	@field:SerializedName("inSurah")
	val inSurah: Int
)

data class Text(

	@field:SerializedName("arab")
	val arab: String
)

data class TranslationAyah(

	@field:SerializedName("id")
	val id: String
)

data class Audio(

	@field:SerializedName("primary")
	val primary: String
)
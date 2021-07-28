package com.rajand.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SurahResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("data")
	val data: List<DataItem>
)

data class DataItem(

	@field:SerializedName("number")
	val number: Int,

	@field:SerializedName("numberOfVerses")
	val numberOfVerses: Int,

	@field:SerializedName("revelation")
	val revelation: Revelation,

	@field:SerializedName("name")
	val name: Name,

	@field:SerializedName("tafsir")
	val tafsir: Tafsir
)

data class Name(

	@field:SerializedName("short")
	val short: String,

	@field:SerializedName("transliteration")
	val transliteration: Transliteration,

	@field:SerializedName("translation")
	val translation: TranslationSurah
)

data class TranslationSurah(

	@field:SerializedName("id")
	val id: String
)

data class Transliteration(

	@field:SerializedName("id")
	val id: String
)

data class Revelation(

	@field:SerializedName("id")
	val id: String
)

data class Tafsir(

	@field:SerializedName("id")
	val id: String
)

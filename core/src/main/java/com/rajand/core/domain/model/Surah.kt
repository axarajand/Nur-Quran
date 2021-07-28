package com.rajand.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Surah(
    val numberSurah: Int,
    val numberOfVerses: Int,
    val name: String,
    val arabName: String,
    val translationName: String,
    val revelation: String,
    val save: Boolean
) : Parcelable

package com.rajand.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ayah(
    val numberAyah: Int,
    val numberSurah: Int,
    val text: String,
    val translationText: String,
    val audio: String,
    val save: Boolean
) : Parcelable

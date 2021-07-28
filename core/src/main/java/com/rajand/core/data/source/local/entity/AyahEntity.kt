package com.rajand.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.*

@Entity(
    tableName = "ayah",
    primaryKeys = ["numberAyah", "numberSurah"],
    foreignKeys = [ForeignKey(
        entity = SurahEntity::class,
        parentColumns = ["numberSurah"],
        childColumns = ["numberSurah"]
    )],
    indices = [Index(value = ["numberAyah"]),
                Index(value = ["numberSurah"])]
)
data class AyahEntity(
    @NonNull
    @ColumnInfo(name = "numberAyah")
    var numberAyah: Int,

    @NonNull
    @ColumnInfo(name = "numberSurah")
    var numberSurah: Int,

    @NonNull
    @ColumnInfo(name = "text")
    var text: String,

    @NonNull
    @ColumnInfo(name = "translationText")
    var translationText: String,

    @NonNull
    @ColumnInfo(name = "audio")
    var audio: String,

    @NonNull
    @ColumnInfo(name = "save")
    var save: Boolean = false
)
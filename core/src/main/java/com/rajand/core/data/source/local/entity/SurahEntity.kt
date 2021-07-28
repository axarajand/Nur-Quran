package com.rajand.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "surah")
data class SurahEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "numberSurah")
    var numberSurah: Int,

    @ColumnInfo(name = "numberOfVerses")
    var numberOfVerses: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "arabName")
    var arabName: String,

    @ColumnInfo(name = "translationName")
    var translationName: String,

    @ColumnInfo(name = "revelation")
    var revelation: String,

    @ColumnInfo(name = "save")
    var save: Boolean = false
)
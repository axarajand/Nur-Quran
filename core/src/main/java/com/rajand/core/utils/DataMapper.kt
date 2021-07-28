package com.rajand.core.utils

import com.rajand.core.data.source.local.entity.AyahEntity
import com.rajand.core.data.source.local.entity.SurahEntity
import com.rajand.core.data.source.remote.response.DataItem
import com.rajand.core.data.source.remote.response.VersesItem
import com.rajand.core.domain.model.Ayah
import com.rajand.core.domain.model.Surah

object DataMapper {

    fun surahMapResponsesToEntities(input: List<DataItem>): List<SurahEntity> {
        val surahList = ArrayList<SurahEntity>()
        input.map {
            val surah = SurahEntity(
                numberSurah = it.number,
                numberOfVerses = it.numberOfVerses,
                name = it.name.transliteration.id,
                arabName = it.name.short,
                translationName = it.name.translation.id,
                revelation = it.revelation.id,
                save = false
            )
            surahList.add(surah)
        }
        return surahList
    }

    fun surahMapEntitiesToDomain(input: List<SurahEntity>): List<Surah> =
        input.map {
            Surah(
                 numberSurah = it.numberSurah,
                 numberOfVerses = it.numberOfVerses,
                 name = it.name,
                 arabName = it.arabName,
                 translationName = it.translationName,
                 revelation = it.revelation,
                 save = it.save
            )
        }

    fun surahMapDomainToEntity(input: Surah) =
        SurahEntity(
            numberSurah = input.numberSurah,
            numberOfVerses = input.numberOfVerses,
            name = input.name,
            arabName = input.arabName,
            translationName = input.translationName,
            revelation = input.revelation,
            save = input.save
        )

    fun ayahMapResponsesToEntities(numberSurah: Int, input: List<VersesItem>): List<AyahEntity> {
        val ayahList = ArrayList<AyahEntity>()
        input.map {
            val ayah = AyahEntity(
                numberAyah = it.number.inSurah,
                numberSurah = numberSurah,
                text = it.text.arab,
                translationText = it.translation.id,
                audio = it.audio.primary,
                save = false
            )
            ayahList.add(ayah)
        }
        return ayahList
    }

    fun ayahMapEntitiesToDomain(input: List<AyahEntity>): List<Ayah> =
        input.map {
            Ayah(
                numberAyah = it.numberAyah,
                numberSurah = it.numberSurah,
                text = it.text,
                translationText = it.translationText,
                audio = it.audio,
                save = it.save
            )
        }

    fun ayahMapDomainToEntity(input: Ayah) =
        AyahEntity(
            numberAyah = input.numberAyah,
            numberSurah = input.numberSurah,
            text = input.text,
            translationText = input.translationText,
            audio = input.audio,
            save = input.save
        )
}
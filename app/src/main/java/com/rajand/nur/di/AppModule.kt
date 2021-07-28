package com.rajand.nur.di

import com.rajand.core.domain.usecase.QuranInteractor
import com.rajand.core.domain.usecase.QuranUseCase
import com.rajand.nur.ui.detail.DetailSurahViewModel
import com.rajand.nur.ui.surah.SurahViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<QuranUseCase> {
        QuranInteractor(get())
    }
}

val viewModelModule = module {
    viewModel { SurahViewModel(get()) }
    viewModel { DetailSurahViewModel(get()) }
}
package com.rajand.save.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.rajand.save.ui.save.SaveViewModel

val saveModule = module {
    viewModel { SaveViewModel(get()) }
}
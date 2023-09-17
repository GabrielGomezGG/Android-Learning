package com.example.koinexample

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { PostRepository() }
    viewModel { MainViewModel(get()) }
}
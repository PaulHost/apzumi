package com.apzumi.challenge.di

import com.apzumi.challenge.ui.RepositoriesViewModel
import org.koin.dsl.module

val vmModule = module {
    single { RepositoriesViewModel(get(), get()) }
}
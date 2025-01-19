package org.company.postoffice.di

import org.company.postoffice.viewmodel.MainViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::MainViewModel)
}
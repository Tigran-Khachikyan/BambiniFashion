package com.example.bambinifashion.app.di

import com.example.bambinifashion.ui.screen.bag.BagViewModel
import com.example.bambinifashion.ui.screen.designers.DesignersViewModel
import com.example.bambinifashion.ui.screen.home.HomeViewModel
import com.example.bambinifashion.ui.screen.menu.MenuViewModel
import com.example.bambinifashion.ui.screen.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        HomeViewModel()
        BagViewModel()
        MenuViewModel()
        SearchViewModel()
        DesignersViewModel()
    }
}

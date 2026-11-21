package com.aparokio.aparok.di.main

import com.aparokio.aparok.navigation.DefaultNavigationBackStackInspector
import com.aparokio.aparok.presentation.navigation.backstack.NavigationBackStackInspector
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindNavigationBackStackInspector(
        impl: DefaultNavigationBackStackInspector
    ): NavigationBackStackInspector
}

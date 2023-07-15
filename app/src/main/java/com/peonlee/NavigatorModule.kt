package com.peonlee

import com.peonlee.core.ui.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigatorModule {
    @Binds
    @Singleton
    abstract fun bindNavigator(
        navigator: PeonLeeNavigator
    ): Navigator
}

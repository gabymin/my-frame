package com.westig.frame.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.westig.frame.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BindViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun bindFlashViewModel(splashViewModel: SplashViewModel): ViewModel

}
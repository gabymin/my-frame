package com.guaniu.benben.base

import com.guaniu.benben.ui.splash.SplashActivity
import com.guaniu.benben.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [BindViewModelModule::class])
abstract class BindActivityModule {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindSplashFragment(): SplashFragment
}
package com.westig.frame.base

import com.westig.frame.ui.splash.SplashActivity
import com.westig.frame.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [BindViewModelModule::class])
abstract class BindActivityModule {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindSplashFragment(): SplashFragment
}
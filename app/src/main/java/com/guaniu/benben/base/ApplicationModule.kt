package com.guaniu.benben.base

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module(includes = [BindActivityModule::class, RepositoryLocalModule::class, RepositoryRemoteModule::class])
abstract class ApplicationModule {
    @Binds
    abstract fun provideContext(application: Application): Context
}
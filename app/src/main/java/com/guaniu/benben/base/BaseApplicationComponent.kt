package com.guaniu.benben.base

import android.app.Application
import com.guaniu.benben.MyBaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,ApplicationModule::class
])
interface BaseApplicationComponent : AndroidInjector<MyBaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): BaseApplicationComponent
    }
}
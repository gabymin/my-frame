package com.westig.frame.base

import dagger.Module
import dagger.android.AndroidInjector
import dagger.internal.Beta
import dagger.multibindings.Multibinds

@Beta
@Module
abstract class AndroidInjectionModule private constructor(){
    @Multibinds
    abstract fun classKeyedInjectorFactories(): Map<Class<*>?, AndroidInjector.Factory<*>?>?

    @Multibinds
    abstract fun stringKeyedInjectorFactories(): Map<String?, AndroidInjector.Factory<*>?>?

}
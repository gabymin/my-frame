package com.westig.frame.base

import dagger.Module
import dagger.internal.Beta

@Beta
@Module(includes = [AndroidInjectionModule::class])
abstract class AndroidSupportInjectionModule private constructor(){
}
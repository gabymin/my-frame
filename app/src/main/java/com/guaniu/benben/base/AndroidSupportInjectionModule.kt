package com.guaniu.benben.base

import dagger.Module
import dagger.internal.Beta

@Beta
@Module(includes = [AndroidInjectionModule::class])
abstract class AndroidSupportInjectionModule private constructor(){
}
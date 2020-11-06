package com.github.aaka.di.modules

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

@Module
class MoshiModule {

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}
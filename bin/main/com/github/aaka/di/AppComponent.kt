package com.github.aaka.di

import com.github.aaka.App
import com.github.aaka.di.modules.MoshiModule
import com.github.aaka.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        MoshiModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(app: App)
}
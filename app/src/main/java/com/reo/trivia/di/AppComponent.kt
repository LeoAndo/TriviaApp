package com.reo.trivia.di

import android.app.Application
import com.reo.trivia.presentation.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        DaoModule::class,
        ServiceModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
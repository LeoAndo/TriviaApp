package com.reo.trivia.di

import com.reo.trivia.presentation.AppLinkActivity
import com.reo.trivia.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun contributeAppLinkActivity(): AppLinkActivity
}
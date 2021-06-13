package com.reo.trivia.di

import com.reo.trivia.presentation.ui.dashboard.DashboardFragment
import com.reo.trivia.presentation.ui.home.HomeFragment
import com.reo.trivia.presentation.ui.notifications.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @ContributesAndroidInjector
    fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    fun contributeNotificationsFragment(): NotificationsFragment
}
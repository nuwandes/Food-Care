package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.home.HomeFragment

@Suppress("unused")
@Module
abstract class ScreeningHomeModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

}

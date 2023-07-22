package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.datamanagement.DataManagementListFragment
import org.southasia.foodcare.ui.devices.DevicesFragment
import org.southasia.foodcare.ui.enumeration.EnumerationFragment
import org.southasia.foodcare.ui.home.HomeFragment
import org.southasia.foodcare.ui.homeenumeration.HomeEnumerationFragment
import org.southasia.foodcare.ui.homeenumerationlist.HomeEmumerationListFragment
import org.southasia.foodcare.ui.logout.LogoutDialogFragment
import org.southasia.foodcare.ui.participantlist.ParticipantListFragment
import org.southasia.foodcare.ui.samplemanagement.SampleMangementFragment
import org.southasia.foodcare.ui.station.StationFragment
import org.southasia.foodcare.ui.usersetting.UserSettingFragment

@Suppress("unused")
@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeEnumerationFragment(): EnumerationFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeEmumerationListFragment(): HomeEmumerationListFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeEnumerationFragment(): HomeEnumerationFragment

    @ContributesAndroidInjector
    abstract fun contributeStationFragment(): StationFragment

    @ContributesAndroidInjector
    abstract fun contributeDevicesFragment(): DevicesFragment


    @ContributesAndroidInjector
    abstract fun contributeSampleMangementFragment(): SampleMangementFragment

    @ContributesAndroidInjector
    abstract fun contributeUserSettingFragment(): UserSettingFragment

    @ContributesAndroidInjector
    abstract fun contributeLogoutDialogFragment(): LogoutDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeDataManagementListFragment() : DataManagementListFragment

    @ContributesAndroidInjector
    abstract fun contributeParticipantListFragment(): ParticipantListFragment

}

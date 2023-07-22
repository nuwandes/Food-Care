package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.codeheck.CodeCheckDialogFragment
import org.southasia.foodcare.ui.enumeration.concent.ConcentFragment
import org.southasia.foodcare.ui.enumeration.concent.reasondialog.ReasonDialogFragment
import org.southasia.foodcare.ui.enumeration.createhousehold.CreateHouseholdFragment
import org.southasia.foodcare.ui.enumeration.householdmembers.HouseholdMembersFragment
import org.southasia.foodcare.ui.enumeration.householdmembers.asigndialog.AsignDialogFragment
import org.southasia.foodcare.ui.enumeration.manualentry.ManualEntryFragment
import org.southasia.foodcare.ui.enumeration.member.AddHouseHoldMemberFragment
import org.southasia.foodcare.ui.enumeration.registergeolocation.RegisterGeolocationFragment
import org.southasia.foodcare.ui.enumeration.scanCode.ScanQRCodeFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogFragment
import org.southasia.foodcare.ui.visitedhouseholds.VisitedHouseholdFragment

@Suppress("unused")
@Module
abstract class EnumerationFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeConcentFragment(): ConcentFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterGeolocationFragment(): RegisterGeolocationFragment

    @ContributesAndroidInjector
    abstract fun contributeScanQRCodeFragment(): ScanQRCodeFragment

    @ContributesAndroidInjector
    abstract fun contributeVisitedHouseholdFragment(): VisitedHouseholdFragment

    @ContributesAndroidInjector
    abstract fun contributeAddHouseHoldMemberFragment(): AddHouseHoldMemberFragment

    @ContributesAndroidInjector
    abstract fun contributeReasonDialogFragment(): ReasonDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeHouseholdMembersFragment(): HouseholdMembersFragment

    @ContributesAndroidInjector
    abstract fun contributeAsignDialogFragment(): AsignDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateHouseholdFragment(): CreateHouseholdFragment


    @ContributesAndroidInjector
    abstract fun contributeErrorDialogFragment(): ErrorDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeManualEntryFragment(): ManualEntryFragment

    @ContributesAndroidInjector
    abstract fun contributeCodeCheckDialogFragment(): CodeCheckDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteCompletedDialogFragment(): org.southasia.foodcare.ui.enumeration.completed.CompletedDialogFragment

}

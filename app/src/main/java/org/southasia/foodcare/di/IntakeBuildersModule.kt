package org.southasia.foodcare.di


import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.intake.cancel.CancelDialogFragment
import org.southasia.foodcare.ui.intake.readings.IntakeReadingsFragment
import org.southasia.foodcare.ui.intake.readings.completed.CompletedDialogFragment
import org.southasia.foodcare.ui.intake.scanbarcode.ScanBarcodeFragment
import org.southasia.foodcare.ui.intake.scanbarcode.ManualEntryBarcodeFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogFragment
import org.southasia.foodcare.ui.stationcheck.StationCheckDialogFragment

@Suppress("unused")
@Module

abstract class IntakeBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeScanBarcodeFragment(): ScanBarcodeFragment

    @ContributesAndroidInjector
    abstract fun contributeErrorDialogFragment(): ErrorDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeManualEntryBarcodeFragment(): ManualEntryBarcodeFragment

    @ContributesAndroidInjector
    abstract fun contrubuteStationCheckDialogFragment(): StationCheckDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteCancelDialogFragment(): CancelDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteIntakeReadingsFragment(): IntakeReadingsFragment

    @ContributesAndroidInjector
    abstract fun contrubuteCompletedDialogFragment(): CompletedDialogFragment
}
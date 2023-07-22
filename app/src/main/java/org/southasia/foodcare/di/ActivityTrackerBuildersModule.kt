package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.activitytracker.activitytracker.ActivityTackeFragment
import org.southasia.foodcare.ui.activitytracker.activitytracker.reason.ReasonDialogFragment
import org.southasia.foodcare.ui.activitytracker.scanbarcode.ScanBarcodeFragment
import org.southasia.foodcare.ui.activitytracker.scanbarcode.manualentry.ManualEntryBarcodeFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogFragment
import org.southasia.foodcare.ui.stationcheck.StationCheckDialogFragment

@Suppress("unused")
@Module
abstract class ActivityTrackerBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeScanBarcodeFragment(): ScanBarcodeFragment


    @ContributesAndroidInjector
    abstract fun contributeErrorDialogFragment(): ErrorDialogFragment

    @ContributesAndroidInjector
    abstract fun contrivuteManualEntryBarcode(): ManualEntryBarcodeFragment

    @ContributesAndroidInjector
    abstract fun contrivuteActivityTackeFragment(): ActivityTackeFragment

    @ContributesAndroidInjector
    abstract fun contrivuteReasonDialogFragment(): ReasonDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteStationCheckDialogFragment(): StationCheckDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteCompletedDialogFragment(): org.southasia.foodcare.ui.activitytracker.activitytracker.completed.CompletedDialogFragment
}

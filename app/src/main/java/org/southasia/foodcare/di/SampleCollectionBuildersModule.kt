package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.codeheck.CodeCheckDialogFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogFragment
import org.southasia.foodcare.ui.samplecollection.bagscanbarcode.BagScanBarcodeFragment
import org.southasia.foodcare.ui.samplecollection.bagscanned.BagScannedFragment
import org.southasia.foodcare.ui.samplecollection.bagscanned.reason.ReasonDialogFragment
import org.southasia.foodcare.ui.samplecollection.fast.FastFragment
import org.southasia.foodcare.ui.samplecollection.fast.reshedule.ResheduleDialogFragment
import org.southasia.foodcare.ui.samplecollection.fasted.FastedFragment
import org.southasia.foodcare.ui.samplecollection.manualentry.ManualEntrySampleBagBarcodeFragment
import org.southasia.foodcare.ui.samplecollection.manualentry.ManualEntrySampleCollectionFragment
import org.southasia.foodcare.ui.samplecollection.scanbarcode.ScanBarcodeFragment
import org.southasia.foodcare.ui.samplecollection.verifyid.VerifyIDFragment
import org.southasia.foodcare.ui.stationcheck.StationCheckDialogFragment

@Suppress("unused")
@Module
abstract class SampleCollectionBuildersModule {


    @ContributesAndroidInjector
    abstract fun contributeVerifyIDFragment(): VerifyIDFragment

    @ContributesAndroidInjector
    abstract fun contributeScanBarcodeFragment(): ScanBarcodeFragment


    @ContributesAndroidInjector
    abstract fun contributeFastFragment(): FastFragment

    @ContributesAndroidInjector
    abstract fun contributeResheduleDialogFragment(): ResheduleDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeFastedFragment(): FastedFragment

    @ContributesAndroidInjector
    abstract fun contributeBagScanBarcodeFragment(): BagScanBarcodeFragment

    @ContributesAndroidInjector
    abstract fun contributeBagScannedFragment(): BagScannedFragment

    @ContributesAndroidInjector
    abstract fun contributeReasonDialogFragment(): ReasonDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeErrorDialogFragment(): ErrorDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeManualEntrySampleCollectionFragment(): ManualEntrySampleCollectionFragment

    @ContributesAndroidInjector
    abstract fun contributeManualEntrySampleBagBarcodeFragment(): ManualEntrySampleBagBarcodeFragment

    @ContributesAndroidInjector
    abstract fun contributeCodeCheckDialogFragment(): CodeCheckDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteStationCheckDialogFragment(): StationCheckDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteCompletedDialogFragment(): org.southasia.foodcare.ui.samplecollection.bagscanned.completed.CompletedDialogFragment
}


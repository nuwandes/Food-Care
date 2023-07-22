package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.ecg.guide.ElectrodeFragment
import org.southasia.foodcare.ui.ecg.guide.PreperationFragment
import org.southasia.foodcare.ui.ecg.trace.TraceFragment
import org.southasia.foodcare.ui.ecg.trace.complete.CompleteDialogFragment
import org.southasia.foodcare.ui.ecg.trace.reason.ReasonDialogFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogFragment
import org.southasia.foodcare.ui.spirometry.cancel.CancelDialogFragment
import org.southasia.foodcare.ui.spirometry.checklist.CheckListFragment
import org.southasia.foodcare.ui.spirometry.guide.GuideMainFragment
import org.southasia.foodcare.ui.spirometry.manualentry.ManualEntrySpirometryFragment
import org.southasia.foodcare.ui.spirometry.record.RecordTestFragment
import org.southasia.foodcare.ui.spirometry.scanbarcode.ScanBarcodeFragment
import org.southasia.foodcare.ui.spirometry.tests.TestFragment
import org.southasia.foodcare.ui.spirometry.verifyid.SpirometryVerifyIDFragment
import org.southasia.foodcare.ui.stationcheck.StationCheckDialogFragment

@Suppress("unused")
@Module
abstract class SpirometryBuilderModule {

    @ContributesAndroidInjector
    abstract fun SpirometryVerifyIDFragment(): SpirometryVerifyIDFragment

    @ContributesAndroidInjector
    abstract fun contributeScanBarcodeFragment(): ScanBarcodeFragment


    @ContributesAndroidInjector
    abstract fun contributeSpirometryGuideMainFragment(): GuideMainFragment

    @ContributesAndroidInjector
    abstract fun contributeTestFragment(): TestFragment

    @ContributesAndroidInjector
    abstract fun contributeCancelDialogFragment(): CancelDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeRecordTestFragment(): RecordTestFragment

    @ContributesAndroidInjector
    abstract fun contributeElectrodeFragment(): ElectrodeFragment

    @ContributesAndroidInjector
    abstract fun contributePreperationFragment(): PreperationFragment

    @ContributesAndroidInjector
    abstract fun contributeTraceFragment(): TraceFragment

    @ContributesAndroidInjector
    abstract fun contributeCompleteDialogFragment(): CompleteDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeReasonDialogFragmentt(): ReasonDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeErrorDialogFragment(): ErrorDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeManualEntryECGFragment(): ManualEntrySpirometryFragment

    @ContributesAndroidInjector
    abstract fun contrubuteStationCheckDialogFragment(): StationCheckDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteCompletedDialogFragment(): org.southasia.foodcare.ui.spirometry.tests.completed.CompletedDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteCheckListFragment(): CheckListFragment

    @ContributesAndroidInjector
    abstract fun contrubuteCancelDialogFragment(): org.southasia.foodcare.ui.spirometry.cancelchecklist.CancelDialogFragment


}
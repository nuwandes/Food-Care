package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.codeheck.CodeCheckDialogFragment
import org.southasia.foodcare.ui.samplemanagement.bloodtesthome.BloodTestHomeFragment
import org.southasia.foodcare.ui.samplemanagement.fastingbloodglucose.FastingBloodGlucoseFragment
import org.southasia.foodcare.ui.samplemanagement.fastingbloodglucose.cancel.CancelDialogFragment
import org.southasia.foodcare.ui.samplemanagement.fastingbloodglucose.completed.CompletedDialogFragment
import org.southasia.foodcare.ui.samplemanagement.hb1ac.Hb1AcFragment
import org.southasia.foodcare.ui.samplemanagement.hdl.HDLFragment
import org.southasia.foodcare.ui.samplemanagement.hemoglobin.HemoglobinFragment
import org.southasia.foodcare.ui.samplemanagement.hogtt.HOGTTFragment
import org.southasia.foodcare.ui.samplemanagement.home.SampleMangementHomeFragment
import org.southasia.foodcare.ui.samplemanagement.lipidprofile.LipidProfileFragment
import org.southasia.foodcare.ui.samplemanagement.pendingsamplelist.PendingSampleListFragment
import org.southasia.foodcare.ui.samplemanagement.storage.StorageFragment
import org.southasia.foodcare.ui.samplemanagement.storage.manualentry.ManualEntryBarcodeFragment
import org.southasia.foodcare.ui.samplemanagement.storage.manualentry.ManualEntryFragment
import org.southasia.foodcare.ui.samplemanagement.storage.reason.ReasonDialogFragment
import org.southasia.foodcare.ui.samplemanagement.storage.scanqrcode.ScanBarcodeFragment
import org.southasia.foodcare.ui.samplemanagement.totalcholesterol.TotalCholesterolFragment
import org.southasia.foodcare.ui.samplemanagement.triglycerides.TriglyceridesFragment
import org.southasia.foodcare.ui.samplemanagement.tubescanbarcode.TubeScanBarcodeFragment
import org.southasia.foodcare.ui.samplemanagement.tubescanbarcode.errordialog.ErrorDialogFragment
import org.southasia.foodcare.ui.samplemanagement.tubescanbarcode.manualentry.TubeScanManualBarcodeFragment

@Suppress("unused")
@Module
abstract class BloodTestBuildersModule {


    @ContributesAndroidInjector
    abstract fun contributePendingSampleListFragment(): PendingSampleListFragment

    @ContributesAndroidInjector
    abstract fun contributeTubeScanBarcodeFragment(): TubeScanBarcodeFragment


    @ContributesAndroidInjector
    abstract fun contributeSampleMangementHomeFragment(): SampleMangementHomeFragment

    @ContributesAndroidInjector
    abstract fun contributeLipidProfileFragment(): LipidProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeFastingBloodGlucoseFragment(): FastingBloodGlucoseFragment

    @ContributesAndroidInjector
    abstract fun contributeCancelDialogFragment(): CancelDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeCompletedDialogFragment(): CompletedDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeHb1AcFragment(): Hb1AcFragment


    @ContributesAndroidInjector
    abstract fun contributeErrorDialogFragment(): ErrorDialogFragment


    @ContributesAndroidInjector
    abstract fun contributeErrorDialogFragmentXX(): org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeTubeScanManualBarcodeFragment(): TubeScanManualBarcodeFragment


    @ContributesAndroidInjector
    abstract fun contributeScanFragment(): org.southasia.foodcare.ui.samplemanagement.storage.scanbarcode.ScanBarcodeFragment

    @ContributesAndroidInjector
    abstract fun contributeStorageFragment(): StorageFragment

    @ContributesAndroidInjector
    abstract fun contributeScanQRCodeFragment(): ScanBarcodeFragment


    @ContributesAndroidInjector
    abstract fun contributeReasonDialogFragment(): ReasonDialogFragment


    @ContributesAndroidInjector
    abstract fun contributeManualEntryFragment(): ManualEntryFragment

    @ContributesAndroidInjector
    abstract fun contributeManualEntryBarcodeFragment(): ManualEntryBarcodeFragment

    @ContributesAndroidInjector
    abstract fun contributeHOGTTFragment(): HOGTTFragment

    @ContributesAndroidInjector
    abstract fun contributeCodeCheckDialogFragment(): CodeCheckDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeTotalCholesterolFragment(): TotalCholesterolFragment

    @ContributesAndroidInjector
    abstract fun contributeHDLFragment(): HDLFragment

    @ContributesAndroidInjector
    abstract fun contributeHemoglobinFragment(): HemoglobinFragment

    @ContributesAndroidInjector
    abstract fun contributeTriglyceridesFragment(): TriglyceridesFragment

    @ContributesAndroidInjector
    abstract fun contrubuteCompletedDialogFragment(): org.southasia.foodcare.ui.samplemanagement.storage.completed.CompletedDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeBloodTestHomeFragment(): BloodTestHomeFragment


}


package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.bodymeasurements.bp.BPFragment
import org.southasia.foodcare.ui.bodymeasurements.bp.info.InfoFragment
import org.southasia.foodcare.ui.bodymeasurements.bp.main.BPMainFragment
import org.southasia.foodcare.ui.bodymeasurements.bp.manual.one.BPManualOneFragment
import org.southasia.foodcare.ui.bodymeasurements.bp.manual.three.BPManualThreeFragment
import org.southasia.foodcare.ui.bodymeasurements.bp.manual.two.BPManualTwoFragment
import org.southasia.foodcare.ui.bodymeasurements.bp.skip.SkipDialogFragment
import org.southasia.foodcare.ui.bodymeasurements.manualentrybp.ManualEntryBodyMeasurement
import org.southasia.foodcare.ui.bodymeasurements.measurements.MeasurementsFragment
import org.southasia.foodcare.ui.bodymeasurements.measurements.second.MeasurementsSecondFragment
import org.southasia.foodcare.ui.bodymeasurements.review.ReviewFragment
import org.southasia.foodcare.ui.bodymeasurements.review.completed.CompletedDialogFragment
import org.southasia.foodcare.ui.bodymeasurements.scanbarcodebp.ScanBarcodeFragment
import org.southasia.foodcare.ui.bodymeasurements.verifyid.VerifyIDFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogFragment
import org.southasia.foodcare.ui.stationcheck.StationCheckDialogFragment

@Suppress("unused")
@Module
abstract class BloodPressureBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeBPFragment(): BPFragment

    @ContributesAndroidInjector
    abstract fun contributeMeasurementsFragment(): MeasurementsFragment

    @ContributesAndroidInjector
    abstract fun contributeReviewFragment(): ReviewFragment

    @ContributesAndroidInjector
    abstract fun contributeVerifyIDFragment(): VerifyIDFragment

    @ContributesAndroidInjector
    abstract fun contributeScanBarcodeFragment(): ScanBarcodeFragment


    @ContributesAndroidInjector
    abstract fun contributeMeasurementsSecondFragment(): MeasurementsSecondFragment

    @ContributesAndroidInjector
    abstract fun contributeBPMainFragment(): BPMainFragment

    @ContributesAndroidInjector
    abstract fun contributeBPManualOneFragment(): BPManualOneFragment

    @ContributesAndroidInjector
    abstract fun contributeBPManualTwoFragment(): BPManualTwoFragment

    @ContributesAndroidInjector
    abstract fun contributeBPManualThreeFragment(): BPManualThreeFragment

    @ContributesAndroidInjector
    abstract fun contributeSkipDialogFragment(): SkipDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeCompletedDialogFragment(): CompletedDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeInfoFragment(): InfoFragment

    @ContributesAndroidInjector
    abstract fun contributeErrorDialogFragment(): ErrorDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteManualEntryBodyMeasurement(): ManualEntryBodyMeasurement

    @ContributesAndroidInjector
    abstract fun contrubuteStationCheckDialogFragment(): StationCheckDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteReasonDialogFragment(): org.southasia.foodcare.ui.bodymeasurements.bp.reason.ReasonDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteCompletedDialogFragment(): org.southasia.foodcare.ui.bodymeasurements.bp.completed.CompletedDialogFragment

}
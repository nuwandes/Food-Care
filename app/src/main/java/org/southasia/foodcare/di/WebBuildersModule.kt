package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.questionnaire.cancel.CancelDialogFragment
import org.southasia.foodcare.ui.questionnaire.languagelist.QuestionnaireListFragment
import org.southasia.foodcare.ui.questionnaire.scanbarcode.ScanBarcodeFragment
import org.southasia.foodcare.ui.questionnaire.scanbarcode.manualentry.ManualEntryBarcodeFragment
import org.southasia.foodcare.ui.questionnaire.web.WebFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogFragment
import org.southasia.foodcare.ui.stationcheck.StationCheckDialogFragment


@Suppress("unused")
@Module
abstract class WebBuildersModule {


    @ContributesAndroidInjector
    abstract fun contributeScanBarcodeFragment(): ScanBarcodeFragment

    @ContributesAndroidInjector
    abstract fun contributeWebFragment(): WebFragment

    @ContributesAndroidInjector
    abstract fun contributeErrorDialogFragment(): ErrorDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeManualEntryBarcodeFragment(): ManualEntryBarcodeFragment

    @ContributesAndroidInjector
    abstract fun contrubuteStationCheckDialogFragment(): StationCheckDialogFragment

    @ContributesAndroidInjector
    abstract fun contrubuteQuestionnaireListFragment(): QuestionnaireListFragment

    @ContributesAndroidInjector
    abstract fun contrubuteCancelDialogFragment(): CancelDialogFragment


}

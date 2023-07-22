package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.participantlist.measurementlist.MeasurementListFragment
import org.southasia.foodcare.ui.participantlist.measurementlist.completevisitcompleted.VisitCompletedDialogFragment
import org.southasia.foodcare.ui.participantlist.measurementlist.completevisitwarning.VisitWarningDialogFragment

@Suppress("unused")
@Module
abstract class MeasurementListBuildersModule{

    @ContributesAndroidInjector
    abstract fun contributeMeasurementListFragment(): MeasurementListFragment

    @ContributesAndroidInjector
    abstract fun contributeVisitCompletedDialogFragment(): VisitCompletedDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeVisitWarningDialogFragment(): VisitWarningDialogFragment


}
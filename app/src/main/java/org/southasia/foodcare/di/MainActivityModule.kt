package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.*

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SettingFragmentBuildersModule::class])
    abstract fun contributeSettingActivity(): SettingActivity

    @ContributesAndroidInjector(modules = [EnumerationFragmentBuildersModule::class])
    abstract fun contributeEnumerationActivity(): EnumerationActivity

    @ContributesAndroidInjector(modules = [BodyMeasurementsBuildersModule::class])
    abstract fun contributeBodyMeasurements(): BodyMeasurementsActivity

    @ContributesAndroidInjector(modules = [RegisterPatientBuildersModule::class])
    abstract fun contributeRegisterPatientActivity(): RegisterPatientActivity

    @ContributesAndroidInjector(modules = [PartcipantAttendanceBuildersModule::class])
    abstract fun contributeParticipantAttendanceActivity(): PatientAttendanceActivity

    @ContributesAndroidInjector(modules = [MeasurementListBuildersModule::class])
    abstract fun contributeMeasurementListActivity(): MeasurementListActivity

    @ContributesAndroidInjector(modules = [ECGBuildersModule::class])
    abstract fun contributeECGActivity(): ECGActivity

    @ContributesAndroidInjector(modules = [FundoscopyBuildersModule::class])
    abstract fun contributeFundoscopyActivity(): FundoscopyActivity

    @ContributesAndroidInjector(modules = [SampleCollectionBuildersModule::class])
    abstract fun contributeSampleCollectionActivity(): SampleCollectionActivity

    @ContributesAndroidInjector(modules = [SampleProcessingBuildersModule::class])
    abstract fun contributeSampleProcessingActivity(): SampleProcessingActivity

    @ContributesAndroidInjector(modules = [SampleStorageBuildersModule::class])
    abstract fun contributeSampleStorageActivity(): SampleStorageActivity

    @ContributesAndroidInjector(modules = [SpirometryBuilderModule::class])
    abstract fun contributeSpirometryActivity(): SpirometryActivity

    @ContributesAndroidInjector(modules = [WebBuildersModule::class])
    abstract fun contributeWebViewActivity(): WebViewActivity

    @ContributesAndroidInjector(modules = [ReportBuildersModule::class])
    abstract fun contributeReportViewActivity(): ReportViewActivity

    @ContributesAndroidInjector(modules = [ActivityTrackerBuildersModule::class])
    abstract fun contributeActivityTrackerActivity(): ActivityTrackerActivity

    @ContributesAndroidInjector(modules = [BloodPressureBuildersModule::class])
    abstract fun contributeBloodPressureActivity(): BloodPressureActivity

    @ContributesAndroidInjector(modules = [ScreeningHomeModule::class])
    abstract fun contributeScreeningHomeActivity(): ScreeningHomeActivity

    @ContributesAndroidInjector(modules = [SampleHomeModule::class])
    abstract fun contributeSampleHomeActivity(): SampleHomeActivity

    @ContributesAndroidInjector(modules = [IntakeBuildersModule::class])
    abstract fun contributeIntakeActivity(): IntakeActivity

    @ContributesAndroidInjector(modules = [BloodTestBuildersModule::class])
    abstract fun contributeBloodTests(): BloodTestActivity


}

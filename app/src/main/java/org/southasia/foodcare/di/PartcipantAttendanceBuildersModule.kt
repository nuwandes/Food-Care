package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.camera.CameraFragment
import org.southasia.foodcare.ui.participantlist.attendance.ParticipantAttendanceFragment
import org.southasia.foodcare.ui.participantlist.attendance.consent.ConsentFragment
import org.southasia.foodcare.ui.participantlist.attendance.consent.reasondialog.ConsentReasonDialogFragment
import org.southasia.foodcare.ui.participantlist.attendance.updateparticipant.UpdateParticipantFragment

@Suppress("unused")
@Module
abstract class PartcipantAttendanceBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeParticipantAttendanceFragment(): ParticipantAttendanceFragment

    @ContributesAndroidInjector
    abstract fun contributeUpdateParticipantFragment(): UpdateParticipantFragment

    @ContributesAndroidInjector
    abstract fun contributeConsentFragment(): ConsentFragment

    @ContributesAndroidInjector
    abstract fun contributeConsentReasonDialogFragment(): ConsentReasonDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeCameraFragment(): CameraFragment

}
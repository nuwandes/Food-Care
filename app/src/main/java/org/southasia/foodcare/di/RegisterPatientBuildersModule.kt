package org.southasia.foodcare.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.southasia.foodcare.ui.camera.CameraFragment
import org.southasia.foodcare.ui.codeheck.CodeCheckDialogFragment
import org.southasia.foodcare.ui.participantlist.statuscompleted.StatusCompletedDialogFragment
import org.southasia.foodcare.ui.registerpatient.basicdetails.BasicDetailsFragment
import org.southasia.foodcare.ui.registerpatient.checklist.CheckListFragment
import org.southasia.foodcare.ui.registerpatient.confirmation.ConfirmationFragment
import org.southasia.foodcare.ui.registerpatient.confirmation.completed.CompletedDialogFragment
import org.southasia.foodcare.ui.registerpatient.explanation.ExplanationFragment
import org.southasia.foodcare.ui.registerpatient.explanation.reasondialog.ExplanationDialogFragment
import org.southasia.foodcare.ui.registerpatient.identification.IdentificationFragment
import org.southasia.foodcare.ui.registerpatient.identification.patientphoto.PatientPhotoFragment
import org.southasia.foodcare.ui.registerpatient.review.ReviewFragment
import org.southasia.foodcare.ui.registerpatient.scanbarcode.ScanBarcodeFragment
import org.southasia.foodcare.ui.registerpatient.scanbarcode.manualentry.ManualEntryBarcodeFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.ScanQRCodeFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.manualentry.ManualEntryQRCodeFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.membersdialog.MembersDialogFragment
import org.southasia.foodcare.ui.registerpatient_new.basicdetails.BasicDetailsFragmentNew
import org.southasia.foodcare.ui.registerpatient_new.confirmation.ConfirmationFragmentNew
import org.southasia.foodcare.ui.registerpatient_new.confirmation.completed.CompletedDialogFragmentNew
import org.southasia.foodcare.ui.registerpatient_new.review.ReviewFragmentNew
import org.southasia.foodcare.ui.registerpatient_new.scanbarcode.ScanBarcodeFragmentNew
import org.southasia.foodcare.ui.registerpatient_new.scanbarcode.manualentry.ManualEntryBarcodeFragmentNew
import org.southasia.foodcare.ui.registerpatient_sg.basicdetails.BasicDetailsFragmentSG
import org.southasia.foodcare.ui.registerpatient_sg.checklist.CheckListFragmentSG
import org.southasia.foodcare.ui.registerpatient_sg.confirmation.ConfirmationFragmentSG
import org.southasia.foodcare.ui.registerpatient_sg.confirmation.completed.CompletedDialogFragmentSG
import org.southasia.foodcare.ui.registerpatient_sg.explanation.ExplanationFragmentSG
import org.southasia.foodcare.ui.registerpatient_sg.review.ReviewFragmentSG
import org.southasia.foodcare.ui.registerpatient_sg.scanbarcode.ScanBarcodeFragmentSG
import org.southasia.foodcare.ui.registerpatient_sg.scanbarcode.manualentry.ManualEntryBarcodeFragmentSG

@Suppress("unused")
@Module
abstract class RegisterPatientBuildersModule {


    @ContributesAndroidInjector
    abstract fun contributeScanQRCodeFragment(): ScanQRCodeFragment

    @ContributesAndroidInjector
    abstract fun contributeMembersDialogFragment(): MembersDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeExplanationFragment(): ExplanationFragment

    @ContributesAndroidInjector
    abstract fun contributeExplanationDialogFragment(): ExplanationDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeIdentificationFragment(): IdentificationFragment

    @ContributesAndroidInjector
    abstract fun contributeReviewFragment(): ReviewFragment

    @ContributesAndroidInjector
    abstract fun contributeBasicDetailsFragment(): BasicDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeScanBarcodeFragment(): ScanBarcodeFragment

    @ContributesAndroidInjector
    abstract fun contributeConfirmationFragment(): ConfirmationFragment

    @ContributesAndroidInjector
    abstract fun contributeCompletedDialogFragment(): CompletedDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeStatusCompletedDialogFragment(): StatusCompletedDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeCameraFragment(): CameraFragment


    @ContributesAndroidInjector
    abstract fun contributePatientPhotoFragment(): PatientPhotoFragment


    @ContributesAndroidInjector
    abstract fun contributeErrorDialogFragment(): ErrorDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeCheckListFragment(): CheckListFragment

    @ContributesAndroidInjector
    abstract fun contributeManualEntryBarcodeFragment(): ManualEntryBarcodeFragment

    @ContributesAndroidInjector
    abstract fun contributeManualEntryQRCodeFragment(): ManualEntryQRCodeFragment

    @ContributesAndroidInjector
    abstract fun contributeCodeCheckDialogFragment(): CodeCheckDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeCheckListFragmentSg(): CheckListFragmentSG

    @ContributesAndroidInjector
    abstract fun contributeBasicDetailsFragmentSG(): BasicDetailsFragmentSG

    @ContributesAndroidInjector
    abstract fun contributeReviewFragmentSG(): ReviewFragmentSG

    @ContributesAndroidInjector
    abstract fun contributeExplanationFragmentSG(): ExplanationFragmentSG

    @ContributesAndroidInjector
    abstract fun contributeScanBarcodeFragmentSG(): ScanBarcodeFragmentSG

    @ContributesAndroidInjector
    abstract fun contributeManualEntryBarcodeFragmentSG(): ManualEntryBarcodeFragmentSG

    @ContributesAndroidInjector
    abstract fun contributeConfirmationFragmentSG(): ConfirmationFragmentSG

    @ContributesAndroidInjector
    abstract fun contributeCompleteDialogFragmentSG(): CompletedDialogFragmentSG


    @ContributesAndroidInjector
    abstract fun contributeBasicDetailsFragmentNew(): BasicDetailsFragmentNew

    @ContributesAndroidInjector
    abstract fun contributeCompleteDialogFragmentNew(): CompletedDialogFragmentNew

    @ContributesAndroidInjector
    abstract fun contributeConfirmationFragmentNew(): ConfirmationFragmentNew

    @ContributesAndroidInjector
    abstract fun contributeReviewFragmentNew(): ReviewFragmentNew

    @ContributesAndroidInjector
    abstract fun contributeManualEntryBarcodeFragmentNew(): ManualEntryBarcodeFragmentNew

    @ContributesAndroidInjector
    abstract fun contributeScanBarcodeFragmentNew(): ScanBarcodeFragmentNew

}

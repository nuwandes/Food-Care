package org.southasia.foodcare.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.southasia.foodcare.ui.activitytracker.activitytracker.ActivityTackeViewModel
import org.southasia.foodcare.ui.bodymeasurements.bodycomposition.BodyCompositionViewModel
import org.southasia.foodcare.ui.bodymeasurements.bp.BPViewModel
import org.southasia.foodcare.ui.bodymeasurements.bp.info.InfoViewModel
import org.southasia.foodcare.ui.bodymeasurements.bp.main.BPMainViewModel
import org.southasia.foodcare.ui.bodymeasurements.bp.manual.one.BPManualOneViewModel
import org.southasia.foodcare.ui.bodymeasurements.bp.manual.three.BPManualThreeViewModel
import org.southasia.foodcare.ui.bodymeasurements.bp.manual.two.BPManualTwoViewModel
import org.southasia.foodcare.ui.bodymeasurements.bp.skip.SkipDialogViewModel
import org.southasia.foodcare.ui.bodymeasurements.height.HeightViewModel
import org.southasia.foodcare.ui.bodymeasurements.hipwaist.HipWaistViewModel
import org.southasia.foodcare.ui.bodymeasurements.home.BodyMeasurementHomeViewModel
import org.southasia.foodcare.ui.bodymeasurements.measurements.MeasurementsViewModel
import org.southasia.foodcare.ui.bodymeasurements.measurements.second.MeasurementsSecondViewModel
import org.southasia.foodcare.ui.bodymeasurements.verifyid.VerifyIDViewModel
import org.southasia.foodcare.ui.codeheck.CodeCheckDialogViewModel
import org.southasia.foodcare.ui.datamanagement.DataManagmentViewModel
import org.southasia.foodcare.ui.devices.DevicesViewModel
import org.southasia.foodcare.ui.ecg.main.InputViewModel
import org.southasia.foodcare.ui.ecg.trace.TraceViewModel
import org.southasia.foodcare.ui.ecg.trace.complete.CompleteDialogViewModel
import org.southasia.foodcare.ui.ecg.verifyid.ECGVerifyIDViewModel
import org.southasia.foodcare.ui.enumeration.EnumerationViewModel
import org.southasia.foodcare.ui.enumeration.concent.ConcentViewModel
import org.southasia.foodcare.ui.enumeration.concent.reasondialog.ReasonDialogViewModel
import org.southasia.foodcare.ui.enumeration.createhousehold.CreateHouseholdViewModel
import org.southasia.foodcare.ui.enumeration.householdmembers.HouseholdMembersViewModel
import org.southasia.foodcare.ui.enumeration.householdmembers.asigndialog.AsignDialogViewModel
import org.southasia.foodcare.ui.enumeration.member.AddHouseHoldMemberViewModel
import org.southasia.foodcare.ui.enumeration.registergeolocation.RegisterGeolocationViewModel
import org.southasia.foodcare.ui.fundoscopy.guide.GuideViewModel
import org.southasia.foodcare.ui.fundoscopy.guide.main.GuideMainViewModel
import org.southasia.foodcare.ui.fundoscopy.reading.FundoscopyReadingViewModel
import org.southasia.foodcare.ui.fundoscopy.verifyid.FundoscopyVerifyIDViewModel
import org.southasia.foodcare.ui.home.HomeViewModel
import org.southasia.foodcare.ui.homeenumeration.HomeEnumerationViewModel
import org.southasia.foodcare.ui.homeenumerationlist.HomeEmumerationListViewModel
import org.southasia.foodcare.ui.login.LoginViewModel
import org.southasia.foodcare.ui.registerpatient.basicdetails.BasicDetailsViewModel
import org.southasia.foodcare.ui.registerpatient.checklist.CheckListViewModel
import org.southasia.foodcare.ui.registerpatient.confirmation.ConfirmationViewModel
import org.southasia.foodcare.ui.registerpatient.confirmation.completed.CompletedDialogViewModel
import org.southasia.foodcare.ui.registerpatient.explanation.ExplanationViewModel
import org.southasia.foodcare.ui.registerpatient.explanation.reasondialog.ExplanationDialogViewModel
import org.southasia.foodcare.ui.registerpatient.identification.IdentificationViewModel
import org.southasia.foodcare.ui.registerpatient.scanbarcode.ScanBarcodeViewModel
import org.southasia.foodcare.ui.registerpatient.scanqrcode.ScanQRCodeViewModel
import org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogViewModel
import org.southasia.foodcare.ui.registerpatient.scanqrcode.membersdialog.MembersDialogViewModel
import org.southasia.foodcare.ui.registerpatient_new.basicdetails.BasicDetailsViewModelNew
import org.southasia.foodcare.ui.registerpatient_new.confirmation.ConfirmationViewModelNew
import org.southasia.foodcare.ui.registerpatient_new.confirmation.completed.CompletedDialogViewModelNew
import org.southasia.foodcare.ui.registerpatient_new.scanbarcode.ScanBarcodeViewModelNew
import org.southasia.foodcare.ui.registerpatient_new.scanbarcode.manualentry.ManualEntryScanBarcodeViewModelNew
import org.southasia.foodcare.ui.registerpatient_sg.basicdetails.BasicDetailsViewModelSG
import org.southasia.foodcare.ui.registerpatient_sg.checklist.CheckListViewModelSG
import org.southasia.foodcare.ui.registerpatient_sg.confirmation.ConfirmationViewModelSG
import org.southasia.foodcare.ui.registerpatient_sg.explanation.ExplanationViewModelSG
import org.southasia.foodcare.ui.registerpatient_sg.scanbarcode.ScanBarcodeViewModelSG
import org.southasia.foodcare.ui.registerpatient_sg.scanbarcode.manualentry.ManualEntryScanBarcodeViewModelSG
import org.southasia.foodcare.ui.samplecollection.bagscanbarcode.BagScanBarcodeViewModel
import org.southasia.foodcare.ui.samplecollection.bagscanned.BagScannedViewModel
import org.southasia.foodcare.ui.samplecollection.fast.FastViewModel
import org.southasia.foodcare.ui.samplecollection.fast.reshedule.ResheduleDialogViewModel
import org.southasia.foodcare.ui.samplecollection.fasted.FastedViewModel
import org.southasia.foodcare.ui.samplemanagement.fastingbloodglucose.FastingBloodGlucoseViewModel
import org.southasia.foodcare.ui.samplemanagement.hb1ac.Hb1AcViewModel
import org.southasia.foodcare.ui.samplemanagement.hogtt.HOGTTViewModel
import org.southasia.foodcare.ui.samplemanagement.home.SampleMangementHomeViewModel
import org.southasia.foodcare.ui.samplemanagement.lipidprofile.LipidProfileViewModel
import org.southasia.foodcare.ui.samplemanagement.pendingsamplelist.PendingSampleListViewModel
import org.southasia.foodcare.ui.samplemanagement.storage.transfer.TransferViewModel
import org.southasia.foodcare.ui.samplemanagement.tubescanbarcode.TubeScanBarcodeViewModel
import org.southasia.foodcare.ui.setting.SettingViewModel
import org.southasia.foodcare.ui.station.StationViewModel
import org.southasia.foodcare.ui.visitedhouseholds.VisitedHouseholdViewModel
import org.southasia.foodcare.viewmodel.GithubViewModelFactory

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(lomeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    abstract fun bindSettingViewModel(settingViewModel: SettingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EnumerationViewModel::class)
    abstract fun bindEnumerationViewModel(enumerationViewModel: EnumerationViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(HomeEnumerationViewModel::class)
    abstract fun bindHomeEnumerationViewModel(homeEnumerationViewModel: HomeEnumerationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StationViewModel::class)
    abstract fun bindStationViewModel(stationViewModel: StationViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(DevicesViewModel::class)
    abstract fun bindDevicesViewModel(devicesViewModel: DevicesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterGeolocationViewModel::class)
    abstract fun bindRegisterGeolocationViewModel(registerGeolocationViewModel: RegisterGeolocationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConcentViewModel::class)
    abstract fun bindConcentViewModel(concentViewModel: ConcentViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(AddHouseHoldMemberViewModel::class)
    abstract fun bindHouseHoldMemberAddViewModel(barCodeGenHouseHoldMemberViewModel: AddHouseHoldMemberViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BPViewModel::class)
    abstract fun bindBPViewModel(bPViewModel: BPViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MeasurementsViewModel::class)
    abstract fun bindMeasurementsViewModel(MeasurementsViewModel: MeasurementsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VerifyIDViewModel::class)
    abstract fun bindVerifyIDViewModel(VerifyIDViewModel: VerifyIDViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeEmumerationListViewModel::class)
    abstract fun bindHomeEmumerationListViewModel(homeEmumeratHomeEmumerationListViewModelionListRepository: HomeEmumerationListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VisitedHouseholdViewModel::class)
    abstract fun bindVisitedHouseholdViewModel(visitedHouseholdViewModel: VisitedHouseholdViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReasonDialogViewModel::class)
    abstract fun bindReasonDialogViewModel(reasonDialogViewModel: ReasonDialogViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(HouseholdMembersViewModel::class)
    abstract fun bindReasonHouseholdMembersViewModel(householdMembersViewModel: HouseholdMembersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AsignDialogViewModel::class)
    abstract fun bindAsignDialogViewModel(asignDialogViewModel: AsignDialogViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(MembersDialogViewModel::class)
    abstract fun bindMembersDialogViewModel(membersDialogViewModel: MembersDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScanQRCodeViewModel::class)
    abstract fun bindScanQRCodeViewModel(scanQRCodeViewModel: ScanQRCodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExplanationDialogViewModel::class)
    abstract fun bindExplanationDialogViewModel(explanationDialogViewModel: ExplanationDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateHouseholdViewModel::class)
    abstract fun bindCreateHouseholdViewModel(createHouseholdViewModel: CreateHouseholdViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExplanationViewModel::class)
    abstract fun bindExplanationViewModel(explanationViewModel: ExplanationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExplanationViewModelSG::class)
    abstract fun bindExplanationViewModelSg(explanationViewModelSG: ExplanationViewModelSG): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BasicDetailsViewModel::class)
    abstract fun bindBasicDetailsViewModel(basicDetailsViewModel: BasicDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BasicDetailsViewModelSG::class)
    abstract fun bindBasicDetailsViewModelSG(basicDetailsViewModelSG: BasicDetailsViewModelSG): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BasicDetailsViewModelNew::class)
    abstract fun bindBasicDetailsViewModelNew(basicDetailsViewModelNew: BasicDetailsViewModelNew): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IdentificationViewModel::class)
    abstract fun bindIdentificationViewModel(identificationViewModel: IdentificationViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.registerpatient.review.ReviewViewModel::class)
    abstract fun bindReviewViewModelX(reviewViewModel: org.southasia.foodcare.ui.registerpatient.review.ReviewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.registerpatient_sg.review.ReviewViewModelSG::class)
    abstract fun bindReviewViewModelXSg(reviewViewModelSg: org.southasia.foodcare.ui.registerpatient_sg.review.ReviewViewModelSG): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.registerpatient_new.review.ReviewViewModelNew::class)
    abstract fun bindReviewViewModelXNew(reviewViewModelSg: org.southasia.foodcare.ui.registerpatient_new.review.ReviewViewModelNew): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScanBarcodeViewModel::class)
    abstract fun bindScanBarcodeViewModel(scanBarcodeViewModel: ScanBarcodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScanBarcodeViewModelSG::class)
    abstract fun bindScanBarcodeViewModelSg(scanBarcodeViewModelSg: ScanBarcodeViewModelSG): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScanBarcodeViewModelNew::class)
    abstract fun bindScanBarcodeViewModelNew(scanBarcodeViewModelNew: ScanBarcodeViewModelNew): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompletedDialogViewModel::class)
    abstract fun bindCompletedDialogViewModel(completedDialogViewModel: CompletedDialogViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(StatusCompletedDialogViewModel::class)
//    abstract fun bindStatusCompletedDialogViewModel(statusCompletedDialogViewModel: StatusCompletedDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompletedDialogViewModelNew::class)
    abstract fun bindCompletedDialogViewModelNew(completedDialogViewModelNew: CompletedDialogViewModelNew): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConfirmationViewModel::class)
    abstract fun bindConfirmationViewModel(confirmationViewModel: ConfirmationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.bodymeasurements.scanbarcode.ScanBarcodeViewModel::class)
    abstract fun bindScanBarcodeViewModelX(scanBarcodeViewModelX: org.southasia.foodcare.ui.bodymeasurements.scanbarcode.ScanBarcodeViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(MeasurementsSecondViewModel::class)
    abstract fun bindMeasurementsSecondViewModel(measurementsSecondViewModel: MeasurementsSecondViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BPMainViewModel::class)
    abstract fun bindBPMainViewModel(bPMainViewModel: BPMainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BPManualOneViewModel::class)
    abstract fun bindBPManualOneViewModel(bPManualOneViewModel: BPManualOneViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BPManualTwoViewModel::class)
    abstract fun bindBPManualTwoViewModel(bPManualTwoViewModel: BPManualTwoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BPManualThreeViewModel::class)
    abstract fun bindBPManualThreeViewModel(bPManualThreeViewModel: BPManualThreeViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SkipDialogViewModel::class)
    abstract fun bindSkipDialogViewModel(skipDialogViewModel: SkipDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.bodymeasurements.review.completed.CompletedDialogViewModel::class)
    abstract fun bindCompletedDialogViewModelX(completedDialogViewModel: org.southasia.foodcare.ui.bodymeasurements.review.completed.CompletedDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfoViewModel::class)
    abstract fun bindInfoViewModel(infoViewModel: InfoViewModel): ViewModel

    //ecg

    @Binds
    @IntoMap
    @ViewModelKey(ECGVerifyIDViewModel::class)
    abstract fun bindECGVerifyIDViewModel(eCGVerifyIDViewModel: ECGVerifyIDViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.ecg.scanbarcode.ScanBarcodeViewModel::class)
    abstract fun bindScanBarcodeViewModelXY(ScanBarcodeViewModel: org.southasia.foodcare.ui.ecg.scanbarcode.ScanBarcodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GuideViewModel::class)
    abstract fun bindGuideViewModel(guideViewModel: GuideViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GuideMainViewModel::class)
    abstract fun bindGuideMainViewModel(guideMainViewModel: GuideMainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InputViewModel::class)
    abstract fun bindInputViewModel(inputViewModel: InputViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FundoscopyVerifyIDViewModel::class)
    abstract fun bindFundoscopyVerifyIDViewModel(fundoscopyVerifyIDViewModel: FundoscopyVerifyIDViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FundoscopyReadingViewModel::class)
    abstract fun bindFundoscopyReadingViewModel(fundoscopyReadingViewModel: FundoscopyReadingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.fundoscopy.scanbarcode.ScanBarcodeViewModel::class)
    abstract fun bindScanBarcodeFragment(ScanBarcodeViewModel: org.southasia.foodcare.ui.fundoscopy.scanbarcode.ScanBarcodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.fundoscopy.displaybarcode.DisplayBarcodeViewModel::class)
    abstract fun bindDisplayBarcodeViewModel(DisplayBarcodeViewModel: org.southasia.foodcare.ui.fundoscopy.displaybarcode.DisplayBarcodeViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.samplecollection.scanbarcode.ScanBarcodeViewModel::class)
    abstract fun bindScanBarcodeViewModelXYZ(guideMainViewModel: org.southasia.foodcare.ui.samplecollection.scanbarcode.ScanBarcodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.samplecollection.verifyid.VerifyIDViewModel::class)
    abstract fun bindVerifyIDViewModelXY(VerifyIDViewModel: org.southasia.foodcare.ui.samplecollection.verifyid.VerifyIDViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ResheduleDialogViewModel::class)
    abstract fun bindResheduleDialogViewModel(ResheduleDialogViewModel: ResheduleDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FastViewModel::class)
    abstract fun bindFastViewModel(fastViewModel: FastViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FastedViewModel::class)
    abstract fun bindFastedViewModel(fastedViewModel: FastedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BagScanBarcodeViewModel::class)
    abstract fun bindBagScanBarcodeViewModel(bagScanBarcodeViewModel: BagScanBarcodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BagScannedViewModel::class)
    abstract fun bindBagScannedViewModel(BagScannedViewModel: BagScannedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.samplecollection.bagscanned.reason.ReasonDialogViewModel::class)
    abstract fun bindReasonDialogViewModelxxx(reasonDialogViewModel: org.southasia.foodcare.ui.samplecollection.bagscanned.reason.ReasonDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PendingSampleListViewModel::class)
    abstract fun bindPendingSampleListViewModel(pendingSampleListViewModel: PendingSampleListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TubeScanBarcodeViewModel::class)
    abstract fun bindTubeScanBarcodeViewModel(tubeScanBarcodeViewModel: TubeScanBarcodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SampleMangementHomeViewModel::class)
    abstract fun bindSampleMangementHomeViewModel(sampleMangementHomeViewModel: SampleMangementHomeViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(LipidProfileViewModel::class)
    abstract fun bindLipidProfileViewModel(lipidProfileViewModel: LipidProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Hb1AcViewModel::class)
    abstract fun bindHb1AcViewModel(hb1AcViewModel: Hb1AcViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FastingBloodGlucoseViewModel::class)
    abstract fun bindFastingBloodGlucoseViewModel(fastingBloodGlucoseViewModel: FastingBloodGlucoseViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(TraceViewModel::class)
    abstract fun bindTraceViewModel(traceViewModel: TraceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompleteDialogViewModel::class)
    abstract fun bindCompleteDialogViewModel(completeDialogViewModel: CompleteDialogViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.ecg.trace.reason.ReasonDialogViewModel::class)
    abstract fun bindReasonDialogViewModelsdewd(completeDialogViewModel: org.southasia.foodcare.ui.ecg.trace.reason.ReasonDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.fundoscopy.reading.reason.ReasonDialogViewModel::class)
    abstract fun bindFReasonDialogViewModelsdewd(reasonDialogViewModel: org.southasia.foodcare.ui.fundoscopy.reading.reason.ReasonDialogViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ErrorDialogViewModel::class)
    abstract fun bindFErrorDialogViewModel(reasonDialogViewModel: ErrorDialogViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(CheckListViewModel::class)
    abstract fun bindCheckListViewModel(checkListViewModel: CheckListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CheckListViewModelSG::class)
    abstract fun bindCheckListViewModelSg(checkListViewModelSG: CheckListViewModelSG): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HOGTTViewModel::class)
    abstract fun bindHOGTTViewModel(hOGTTViewModel: HOGTTViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.questionnaire.scanbarcode.ScanBarcodeViewModel::class)
    abstract fun bindScanBarcodeViewModelQQ(hOGTTViewModel: org.southasia.foodcare.ui.questionnaire.scanbarcode.ScanBarcodeViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.samplemanagement.storage.samplelist.PendingSampleListViewModel::class)
    abstract fun bindPendingSampleListViewModelQQ(pendingSampleListViewModel: org.southasia.foodcare.ui.samplemanagement.storage.samplelist.PendingSampleListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransferViewModel::class)
    abstract fun bindTransferViewModel(transferViewModel: TransferViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ActivityTackeViewModel::class)
    abstract fun bindActivityTackeViewModel(activityTackeViewModel: ActivityTackeViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(org.southasia.foodcare.ui.activitytracker.activitytracker.reason.ReasonDialogViewModel::class)
    abstract fun bindReasonDialogViewModelXX(ReasonDialogViewModel: org.southasia.foodcare.ui.activitytracker.activitytracker.reason.ReasonDialogViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(CodeCheckDialogViewModel::class)
    abstract fun bindCodeCheckDialogViewModel(codeCheckDialogViewModel: CodeCheckDialogViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(BodyMeasurementHomeViewModel::class)
    abstract fun bindCodeBodyMeasurementHomeViewModel(bodyMeasurementHomeViewModel: BodyMeasurementHomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HeightViewModel::class)
    abstract fun bindHeightViewModel(heightViewModel: HeightViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BodyCompositionViewModel::class)
    abstract fun bindBodyCompositionViewModel(bodyCompositionViewModel: BodyCompositionViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(HipWaistViewModel::class)
    abstract fun bindHipWaistViewModel(hipWaistViewModel: HipWaistViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: GithubViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(DataManagmentViewModel::class)
    abstract  fun bindDataManagmentViewModel(dataManagmentViewModel: DataManagmentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ManualEntryScanBarcodeViewModelSG::class)
    abstract  fun bindManualEntryScanBarcodeViewModelSg(dataManualEntryScanBarcodeViewModelSG: ManualEntryScanBarcodeViewModelSG) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConfirmationViewModelSG::class)
    abstract  fun bindConfirmationViewModelSg(dataConfirmationViewModelSG: ConfirmationViewModelSG) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConfirmationViewModelNew::class)
    abstract  fun bindConfirmationViewModelNew(dataConfirmationViewModelNew: ConfirmationViewModelNew) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ManualEntryScanBarcodeViewModelNew::class)
    abstract  fun bindManualEntryScanBarcodeViewModelNew(dataManualEntryScanBarcodeViewModelNew: ManualEntryScanBarcodeViewModelNew) : ViewModel


}

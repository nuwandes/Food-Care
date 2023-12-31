package org.southasia.foodcare.ui.registerpatient_new.scanbarcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.*
import org.southasia.foodcare.R
import org.southasia.foodcare.binding.FragmentDataBindingComponent
import org.southasia.foodcare.databinding.ScanBarcodePatientGenFragmentBinding
import org.southasia.foodcare.databinding.ScanBarcodePatientGenFragmentNewBinding
import org.southasia.foodcare.databinding.ScanBarcodePatientGenFragmentSgBinding
import org.southasia.foodcare.di.Injectable
import org.southasia.foodcare.ui.codeheck.CodeCheckDialogFragment
import org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogFragment
import org.southasia.foodcare.util.*
import org.southasia.foodcare.vo.Status
import org.southasia.foodcare.vo.request.ParticipantMeta
import timber.log.Timber
import javax.inject.Inject


class ScanBarcodeFragmentNew : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var binding by autoCleared<ScanBarcodePatientGenFragmentNewBinding>()

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    @Inject
    lateinit var viewModel: ScanBarcodeViewModelNew

    private var participantMeta: ParticipantMeta? = null

    private var concentPhoto: String? = null

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
//            concentPhoto = arguments?.getString("concentPhotoPath")!!
            participantMeta = arguments?.getParcelable<ParticipantMeta>("participantMeta")!!
        } catch (e: KotlinNullPointerException) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<ScanBarcodePatientGenFragmentNewBinding>(
            inflater,
            R.layout.scan_barcode_patient_gen_fragment_new,
            container,
            false
        )
        binding = dataBinding
        setHasOptionsMenu(true)
        val appCompatActivity = requireActivity() as AppCompatActivity
        appCompatActivity.setSupportActionBar(binding.detailToolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.root.hideKeyboard()

        codeScanner = CodeScanner(context!!, binding.scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ONE_DIMENSIONAL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not
        codeScanner.startPreview()

        codeScanner.decodeCallback = DecodeCallback {
            activity?.runOnUiThread {
                Toast.makeText(activity!!, getString(R.string.scan_result) + ": ${it.text}", Toast.LENGTH_LONG).show()

                val checkSum = validateChecksum(it.text, Constants.TYPE_PARTICIPANT)
                if (!checkSum.error) {
                    participantMeta?.body?.screeningId = it.text
                    viewModel.setScreeningId(participantMeta?.body?.screeningId)
                } else {
                    codeScanner.startPreview()
                    val errorDialogFragment = ErrorDialogFragment()
                    errorDialogFragment.setErrorMessage(getString(R.string.invalid_code))
                    errorDialogFragment.show(fragmentManager!!)
                    //Crashlytics.logException(Exception(getString(R.string.invalid_code)))

                }
            }
        }
        codeScanner.errorCallback = ErrorCallback {
            // or ErrorCallback.SUPPRESS
            activity?.runOnUiThread {
                Toast.makeText(
                    activity!!, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        viewModel.screeningIdCheck?.observe(this, Observer { householdId ->
            // //L.d(householdId.toString())
            if (householdId?.status == Status.SUCCESS) {
                codeScanner.startPreview()
                val codeCheckDialogFragment = CodeCheckDialogFragment()
                codeCheckDialogFragment.show(fragmentManager!!)
            } else if (householdId?.status == Status.ERROR) {
                findNavController().navigate(
                    R.id.action_global_confirmationFragment,
                    bundleOf("participantMeta" to participantMeta, "concentPhotoPath" to concentPhoto)
                )
            }

        })


//        if (BuildConfig.DEBUG) {
//            val barcode = "PAA-1042-9"
//            participantMeta?.body?.screeningId = barcode
//            viewModel.setScreeningId(barcode)
//        }

        binding.buttonManualEntry.singleClick {

            binding.root.hideKeyboard()
            Timber.d(participantMeta.toString())
            navController().navigate(
                R.id.action_scanBarcodeFragmentNew_to_scanBarcodeManualFragmentNew,
                bundleOf("participantMeta" to participantMeta, "concentPhotoPath" to concentPhoto)
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                return navController().popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScanner.releaseResources()
    }


    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}

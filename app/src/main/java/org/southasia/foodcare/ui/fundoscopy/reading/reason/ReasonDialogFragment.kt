package org.southasia.foodcare.ui.fundoscopy.reading.reason

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.birbit.android.jobqueue.JobManager
import org.southasia.foodcare.R
import org.southasia.foodcare.binding.FragmentDataBindingComponent
import org.southasia.foodcare.databinding.FundocopyReasonDialogFragmentBinding
import org.southasia.foodcare.di.Injectable
import org.southasia.foodcare.ui.ecg.trace.reason.ReasonDialogFragment
import org.southasia.foodcare.ui.fundoscopy.reading.completed.CompletedDialogFragment
import org.southasia.foodcare.util.autoCleared
import org.southasia.foodcare.util.hideKeyboard
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.Status
import org.southasia.foodcare.vo.request.CancelRequest
import org.southasia.foodcare.vo.request.ParticipantRequest
import javax.inject.Inject


class ReasonDialogFragment : DialogFragment(), Injectable {

    val TAG = ReasonDialogFragment::class.java.getSimpleName()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var binding by autoCleared<FundocopyReasonDialogFragmentBinding>()

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    @Inject
    lateinit var viewModel: ReasonDialogViewModel

    lateinit var cancelRequest: CancelRequest

    private var participant: ParticipantRequest? = null
    private var problematic: String? = null
    private var problemWith: String? = null

    @Inject
    lateinit var jobManager: JobManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            participant = arguments?.getParcelable<ParticipantRequest>("participant")!!
        } catch (e: KotlinNullPointerException) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<FundocopyReasonDialogFragmentBinding>(
            inflater,
            R.layout.fundocopy_reason_dialog_fragment,
            container,
            false
        )
        binding = dataBinding
        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cancelRequest = CancelRequest(stationType = "fundoscopy")
        binding.radioGroupProblemEye.setOnCheckedChangeListener { radioGroup, _ ->
            // println("i $i" + radioGroup.checkedRadioButtonId)
            binding.OtherNote.visibility = View.GONE
            binding.OtherNote.text?.clear()
            when (radioGroup.checkedRadioButtonId) {

                R.id.radioButtonLeftEye -> problematic = getString(R.string.fundus_machine_technical_issue)
                R.id.radioButtonRightEye -> problematic = getString(R.string.fundus_machine_not_available)
                R.id.radioButtonBothEyes -> problematic = getString(R.string.fundus_participant_eye_issue)
                R.id.radioButtonRefused -> problematic = getString(R.string.fundus_participant_refused)
                R.id.radioButtonUnableToSit -> problematic = getString(R.string.fundus_participant_unable_to_sit)
                R.id.radioButtonOther ->
                {
                    binding.OtherNote.visibility = View.VISIBLE
                    problematic = binding.OtherNote.text.toString()
                }

                else -> {
                    // binding.textViewError.text = getString(R.string.app_error_please_select_one)
                    binding.textViewError.visibility = View.GONE
                }
            }
            binding.executePendingBindings()
        }

       // binding.OtherNote.text.on
        binding.buttonAcceptAndContinue.singleClick {
            if( !binding.OtherNote.text.isNullOrEmpty())
                problematic = binding.OtherNote.text.toString()

            binding.root.hideKeyboard()
            if (binding.radioGroupProblemEye.checkedRadioButtonId == -1 || problematic.isNullOrEmpty()) {
                binding.textViewError.text = getString(R.string.app_error_please_select_one)
                binding.textViewError.visibility = View.VISIBLE

            }
            else
            {
                binding.textViewError.text = ""
                binding.textViewError.visibility = View.GONE

                cancelRequest.reason = problematic  //"Problematic eye: $problematic, Problem with?: $problemWith"
                cancelRequest.comment = binding.comment.text.toString()
                cancelRequest.syncPending =!isNetworkAvailable()
                cancelRequest.screeningId = participant?.screeningId!!
                viewModel.setLogin(participant, cancelRequest)
            }


        }

        viewModel.cancelId?.observe(this, Observer { householdResource ->
            if (householdResource?.status == Status.SUCCESS) {
                dismiss()
                val completedDialogFragment = CompletedDialogFragment()
                completedDialogFragment.arguments = bundleOf("is_cancel" to true)
                completedDialogFragment.show(fragmentManager!!)
            }
        })
        binding.buttonCancel.singleClick {
            binding.root.hideKeyboard()
            dismiss()
        }
    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // the content
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // creating the fullscreen dialog
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        return dialog
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, TAG)
    }

}

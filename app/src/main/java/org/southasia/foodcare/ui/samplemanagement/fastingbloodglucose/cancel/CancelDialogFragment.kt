package org.southasia.foodcare.ui.samplemanagement.fastingbloodglucose.cancel

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
import org.southasia.foodcare.databinding.FbgCancelDialogFragmentBinding
import org.southasia.foodcare.di.Injectable
import org.southasia.foodcare.ui.ecg.trace.reason.ReasonDialogFragment
import org.southasia.foodcare.ui.samplemanagement.fastingbloodglucose.completed.CompletedDialogFragment
import org.southasia.foodcare.util.autoCleared
import org.southasia.foodcare.util.hideKeyboard
import org.southasia.foodcare.util.shoKeyboard
import org.southasia.foodcare.vo.Status
import org.southasia.foodcare.vo.request.CancelRequest
import org.southasia.foodcare.vo.request.ParticipantRequest
import javax.inject.Inject

class CancelDialogFragment : DialogFragment(), Injectable {

    val TAG = ReasonDialogFragment::class.java.getSimpleName()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var binding by autoCleared<FbgCancelDialogFragmentBinding>()
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    @Inject
    lateinit var viewModel: CancelDialogViewModel

    lateinit var cancelRequest: CancelRequest

    private var participant: ParticipantRequest? = null
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
        val dataBinding = DataBindingUtil.inflate<FbgCancelDialogFragmentBinding>(
            inflater,
            R.layout.fbg_cancel_dialog_fragment,
            container,
            false
        )
        binding = dataBinding
        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cancelRequest = CancelRequest(stationType = "sample")
        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            // println("i $i" + radioGroup.checkedRadioButtonId)
            when (radioGroup.checkedRadioButtonId) {
                R.id.radioButtonFBGRefused -> cancelRequest.reason =
                    getString(R.string.fbg_refused)
                R.id.radioButtonFBGDeviseIssue -> cancelRequest.reason =
                    getString(R.string.fbg_faulty_device)
                R.id.radioButtonFBGUnable -> cancelRequest.reason =
                    getString(R.string.fbg_unable_to_collect)
                else -> {
                    // binding.textViewError.text = getString(R.string.app_error_please_select_one)
                    binding.textViewError.visibility = View.GONE
                }
            }
            if (radioGroup.checkedRadioButtonId == R.id.radioButtonOther) {
                binding.textInputEditTextOther.visibility = View.VISIBLE
                binding.textInputEditTextOther.shoKeyboard()
            } else {
                binding.textInputEditTextOther.visibility = View.GONE
            }
            binding.executePendingBindings()
        }

        binding.buttonAcceptAndContinue.setOnClickListener {
            binding.root.hideKeyboard()
            if (binding.radioGroup.checkedRadioButtonId == -1) {
                binding.textViewError.text = getString(R.string.app_error_please_select_one)
                binding.textViewError.visibility = View.VISIBLE

            } else  {

                binding.textViewError.text = "";
                binding.textViewError.visibility = View.GONE

                cancelRequest.comment = binding.comment.text.toString()

                if(binding.radioButtonFBGDeviseIssue.isChecked)
                    cancelRequest.reason = binding.radioButtonFBGDeviseIssue.text.toString()
                else if(binding.radioButtonFBGRefused.isChecked)
                    cancelRequest.reason = binding.radioButtonFBGRefused.text.toString()
                else
                    cancelRequest.reason = binding.radioButtonFBGUnable.text.toString()

                cancelRequest.screeningId = participant?.screeningId!!
                cancelRequest.syncPending = !isNetworkAvailable()
                viewModel.setLogin(participant, cancelRequest)

            }


        }

        viewModel.cancelId?.observe(this, Observer { cancelObserver ->
            if (cancelObserver?.status == Status.SUCCESS) {
                dismiss()

                val completedDialogFragment = CompletedDialogFragment()
                completedDialogFragment.arguments = bundleOf("is_cancel" to true)
                completedDialogFragment.show(fragmentManager!!)

            } else if (cancelObserver?.status == Status.ERROR) {
                binding.textViewError.text = cancelObserver.message?.message.toString()
            }
        })

        binding.buttonCancel.setOnClickListener {
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
package org.southasia.foodcare.ui.participantlist.measurementlist.completevisitwarning

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.crashlytics.android.Crashlytics
import org.southasia.foodcare.MainActivity
import org.southasia.foodcare.R
import org.southasia.foodcare.binding.FragmentDataBindingComponent
import org.southasia.foodcare.databinding.CompletedBodyMeasuementDialogFragmentBinding
import org.southasia.foodcare.databinding.VisitCompletedDialogFragmentBinding
import org.southasia.foodcare.databinding.VisitWarningDialogFragmentBinding
import org.southasia.foodcare.di.Injectable
import org.southasia.foodcare.util.autoCleared
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.ParticipantListItem
import org.southasia.foodcare.vo.Status
import javax.inject.Inject

class VisitWarningDialogFragment : DialogFragment(), Injectable {

    val TAG = VisitWarningDialogFragment::class.java.getSimpleName()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    var binding by autoCleared<VisitWarningDialogFragmentBinding>()
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    @Inject
    lateinit var warningDialogViewModel: VisitWarningDialogViewModel

    var isCancel: Boolean = false

    private var participant: ParticipantListItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            isCancel = arguments?.getBoolean("is_cancel")!!
            participant = arguments?.getParcelable<ParticipantListItem>("participant")!!
        } catch (e: KotlinNullPointerException) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<VisitWarningDialogFragmentBinding>(
            inflater,
            R.layout.visit_warning_dialog_fragment,
            container,
            false
        )
        binding = dataBinding
        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.isCancel = isCancel

        binding.homeButton.singleClick {
            dismiss()
        }

        warningDialogViewModel.setFollowUpParticipant(participant!!, participant!!.participant_id)

        binding.continueButton.singleClick {

            warningDialogViewModel.updateParticipantFollowUp(participant!!, participant!!.participant_id)
        }

        warningDialogViewModel.participantFollowUpStatusUpdate?.observe(this, Observer { assertsResource ->
            if (assertsResource?.status == Status.SUCCESS) {
                println(assertsResource.data?.data)
                if (assertsResource.data != null) {

                    activity?.finish()
                    dismiss()
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)


                } else {
                    Log.d("WARNING_FRAGMENT", "SUBMIT_BUTTON_FAILED" + assertsResource.message.toString())
                    Toast.makeText(activity, "Unable to update the participant via " + assertsResource.message.toString(), Toast.LENGTH_LONG).show()
                    Crashlytics.logException(Exception("Participant Update " + assertsResource.message.toString()))
                    binding.executePendingBindings()
                }
            }
        })


    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                return navController().navigateUp()
            }
        }
        return super.onOptionsItemSelected(item)
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

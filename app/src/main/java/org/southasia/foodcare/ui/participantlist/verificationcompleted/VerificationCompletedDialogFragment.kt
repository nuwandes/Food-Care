package org.southasia.foodcare.ui.participantlist.verificationcompleted

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.status_completed_dialog_fragment.*
import org.southasia.foodcare.R
import org.southasia.foodcare.binding.FragmentDataBindingComponent
import org.southasia.foodcare.databinding.StatusCompletedDialogFragmentBinding
import org.southasia.foodcare.databinding.VerificationCompletedDialogFragmentBinding
import org.southasia.foodcare.util.autoCleared
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.ParticipantListItem

class VerificationCompletedDialogFragment : DialogFragment() {

    val TAG = VerificationCompletedDialogFragment::class.java.getSimpleName()

//    var prefs : SharedPreferences? = null

//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory

    var binding by autoCleared<VerificationCompletedDialogFragmentBinding>()

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

//    @Inject
//    lateinit var statusCompletedDialogViewModel: StatusCompletedDialogViewModel

    private var participant: ParticipantListItem? = null

    var prefs : SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            participant = arguments?.getParcelable<ParticipantListItem>("single_participant")!!
        } catch (e: KotlinNullPointerException) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<VerificationCompletedDialogFragmentBinding>(
            inflater,
            R.layout.verification_completed_dialog_fragment,
            container,
            false
        )
        binding = dataBinding
        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        prefs = PreferenceManager.getDefaultSharedPreferences(context)

        btnContinue.singleClick {
            dismiss()

            // measurement list displays with nav control, if validation path is ok

            findNavController().navigate(
                    R.id.action_global_ConsentFragment,
                    bundleOf("participant" to participant!!))

//            val json: String = MemberTypeConverters.gson.toJson(participant)
//            prefs?.edit()?.putString("selected_participant", json)?.apply()
//            val intent = Intent(activity, MeasurementListActivity::class.java)
//            startActivity(intent)

        }
        btnCancel.singleClick {
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

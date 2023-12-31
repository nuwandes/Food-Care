package org.southasia.foodcare.ui.registerpatient_new.confirmation.completed

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.*
import android.widget.RelativeLayout
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.southasia.foodcare.R
import org.southasia.foodcare.RegisterPatientActivity
import org.southasia.foodcare.binding.FragmentDataBindingComponent
import org.southasia.foodcare.databinding.CompletedDialogFragmentBinding
import org.southasia.foodcare.databinding.CompletedDialogFragmentNewBinding
import org.southasia.foodcare.databinding.CompletedDialogFragmentSgBinding
import org.southasia.foodcare.di.Injectable
import org.southasia.foodcare.util.autoCleared
import org.southasia.foodcare.util.singleClick
import javax.inject.Inject

class CompletedDialogFragmentNew : DialogFragment(), Injectable {

    val TAG = CompletedDialogFragmentNew::class.java.getSimpleName()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var binding by autoCleared<CompletedDialogFragmentNewBinding>()
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    @Inject
    lateinit var confirmationdialogViewModel: CompletedDialogViewModelNew

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<CompletedDialogFragmentNewBinding>(
            inflater,
            R.layout.completed_dialog_fragment_new,
            container,
            false
        )
        binding = dataBinding
        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.newMemberButton.singleClick {
            activity?.finish()
            dismiss()
            val intent = Intent(activity, RegisterPatientActivity::class.java)
            startActivity(intent)
        }
        binding.homeButton.singleClick {
            activity?.finish()
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

package org.southasia.foodcare.ui.login.signup


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.ilhasoft.support.validation.Validator
import org.southasia.foodcare.R
import org.southasia.foodcare.binding.FragmentDataBindingComponent
import org.southasia.foodcare.databinding.SignUpFragmentBinding
import org.southasia.foodcare.di.Injectable
import org.southasia.foodcare.ui.login.signup.completed.CompletedDialogFragment
import org.southasia.foodcare.util.autoCleared
import org.southasia.foodcare.util.getLocalTimeString
import org.southasia.foodcare.util.hideKeyboard
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.MetaFoodCare
import org.southasia.foodcare.vo.Status
import org.southasia.foodcare.vo.request.SignUpData
import org.southasia.foodcare.vo.request.SignUpRequest
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.*
import javax.inject.Inject

class SignUpFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    var binding by autoCleared<SignUpFragmentBinding>()
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    @Inject
    lateinit var signUpViewModel: SignUpViewModel

    private lateinit var validator: Validator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<SignUpFragmentBinding>(
            inflater,
            R.layout.sign_up_fragment,
            container,
            false
        )

        binding = dataBinding
        validator = Validator(binding)
        binding.textViewVesion.text = getApplicationVersionName()
        return dataBinding.root
    }

    private fun getApplicationVersionName(): String {

        try {
            val packageInfo = activity?.getPackageManager()?.getPackageInfo(activity?.getPackageName(), 0)
            return packageInfo?.versionName!!
        } catch (ignored: Exception) {
        }

        return ""
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.textViewLogin.singleClick {

            findNavController().navigate(R.id.action_signupFrag_to_loginFragment)
        }
        binding.buttonLogin.singleClick {

            val sTime: String = convertTimeTo24Hours()
            val sDate: String = getDate()
            val sDateTime:String = sDate + " " + sTime

            val meta = MetaFoodCare(startTime = sDateTime)

            meta?.startTime = sDateTime

            if (isNetworkAvailable())
            {
                if (validator.validate())
                {
                    binding.progressBar.visibility = View.VISIBLE
                    val mPattern: Pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\\^&\\*])(?=.{8,})")

                    val matche: Matcher = mPattern.matcher(binding.textInputEditTextPassword.text.toString())
                    binding.root.hideKeyboard()

                    if (validateSignUpData())
                    {
                        if(!matche.find())
                        {
                            binding.textInputLayoutPassword.error = getString(R.string.passowrd_reg_error)
                            binding.progressBar.visibility = View.GONE
                        }
                        else
                        {
                            val mSignUpData = SignUpData(
                                name = binding.textInputEditTextName.text.toString(),
                                phone = binding.textInputEditTextPhone.text.toString(),
                                email = binding.textInputEditTextEmail.text.toString(),
                                password = binding.textInputEditTextPassword.text.toString(),
                                user_type = "booking"
                            )
                            val mSignUpRequest = SignUpRequest(meta = meta, body = mSignUpData)
                            signUpViewModel.setSignupData(mSignUpRequest)
                        }
                    }
                }
            }
            else
            {
                Toast.makeText(activity!!, "Check your internet connection", Toast.LENGTH_LONG).show()
                binding.progressBar.visibility = View.GONE
            }
        }

        signUpViewModel.syncSignupRequest?.observe(this, Observer { signUpProcess ->

            if (signUpProcess?.status == Status.SUCCESS)
            {
                val completedDialogFragment = CompletedDialogFragment()
                completedDialogFragment.arguments = bundleOf("message" to "Successfully signed up!!!")
                completedDialogFragment.show(fragmentManager!!)
                binding.progressBar.visibility = View.GONE
            }
            else if(signUpProcess?.status == Status.ERROR)
            {
                Toast.makeText(activity!!, "Sign Up failed", Toast.LENGTH_LONG).show()
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun validateSignUpData() : Boolean
    {
        if (binding.textInputEditTextName == null || binding.textInputEditTextName!!.text!!.equals(""))
        {
            Toast.makeText(activity!!, "Invalid name", Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
            return false
        }
        else if (binding.textInputEditTextPhone == null || binding.textInputEditTextPhone!!.text!!.equals(""))
        {
            Toast.makeText(activity!!, "Invalid phone number", Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
            return false
        }
        else if (binding.textInputEditTextEmail == null || binding.textInputEditTextEmail!!.text!!.equals(""))
        {
            Toast.makeText(activity!!, "Invalid email address", Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
            return false
        }
        else
        {
            return true
        }
    }

    private fun convertTimeTo24Hours(): String
    {
        val now: Calendar = Calendar.getInstance()
        val inputFormat: DateFormat = SimpleDateFormat("MMM DD, yyyy HH:mm:ss")
        val outputformat: DateFormat = SimpleDateFormat("HH:mm")
        val date: Date
        val output: String
        try{
            date= inputFormat.parse(now.time.toLocaleString())
            output = outputformat.format(date)
            return output
        }catch(p: ParseException){
            return ""
        }
    }

    private fun getDate(): String
    {
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm")
        val outputformat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date
        val output: String
        try{
            date= inputFormat.parse(binding.root.getLocalTimeString())
            output = outputformat.format(date)

            return output
        }catch(p: ParseException){
            return ""
        }
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}

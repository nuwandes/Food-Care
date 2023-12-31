package org.southasia.foodcare.ui.registerpatient_sg.checklist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.check_list_fragment_sg.*
import org.southasia.foodcare.R
import org.southasia.foodcare.binding.FragmentDataBindingComponent
import org.southasia.foodcare.databinding.CheckListFragmentSgBinding
import org.southasia.foodcare.di.Injectable
import org.southasia.foodcare.ui.registerpatient.scanqrcode.errordialog.ErrorDialogFragment
import org.southasia.foodcare.util.autoCleared
import org.southasia.foodcare.util.getLocalTimeString
import org.southasia.foodcare.util.hideKeyboard
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.Meta
import org.southasia.foodcare.vo.Status
import org.southasia.foodcare.vo.User
import org.southasia.foodcare.vo.request.HouseholdRequest
import org.southasia.foodcare.vo.request.Member
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class CheckListFragmentSG : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var binding by autoCleared<CheckListFragmentSgBinding>()

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    @Inject
    lateinit var viewModel: CheckListViewModelSG
    var user: User? = null
    var meta: Meta? = null

    var memberList: java.util.ArrayList<Member>? = null

    var household: HouseholdRequest? = null

    var membersResourceList: List<Member>? = null

    var householdId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<CheckListFragmentSgBinding>(
            inflater,
            R.layout.check_list_fragment_sg,
            container,
            false
        )
        binding = dataBinding
        setHasOptionsMenu(true)
        val appCompatActivity = requireActivity() as AppCompatActivity
        appCompatActivity.setSupportActionBar(binding.detailToolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.root.hideKeyboard()

        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.setLifecycleOwner(this)

        viewModel.setUser("user")
        viewModel.user?.observe(this, Observer { userData ->
            if (userData?.data != null) {
                // setupNavigationDrawer(userData.data)
                user = userData.data

                val stTime: String = convertTimeTo24Hours()
                val stDate: String = getDate()
                val stDateTime:String = stDate + " " + stTime

                meta = Meta(collectedBy = user?.id, startTime = stDateTime)
                meta?.registeredBy = user?.id
            }

        })

        viewModel.members?.observe(this, Observer { membersResource ->
            binding.resource = membersResource
            if (membersResource?.status == Status.SUCCESS) {
                if (membersResource.data?.data?.isNotEmpty()!!) {
                    memberList = ArrayList(membersResource.data.data)
                } else {
                    memberList = ArrayList()
                }
                viewModel.setEnumarationId(householdId)
            } else if (membersResource?.status == Status.ERROR) {
                val errorDialogFragment = ErrorDialogFragment()
                errorDialogFragment.setErrorMessage(membersResource.message?.message!!)
                if (!errorDialogFragment.isVisible) {
                    errorDialogFragment.show(fragmentManager!!)
                }
            }
            binding.executePendingBindings()

        })

        viewModel.houseHoldBody?.observe(this, Observer {
            if (it?.status == Status.SUCCESS) {
                household = it.data?.data?.household
            }
        })

        viewModel.membersOfline?.observe(this, Observer {
            binding.resource = it
            if (it?.status == Status.SUCCESS) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        membersResourceList = it.data;
                    } else if (it.data.isEmpty()) {
                        membersResourceList = ArrayList()
                    } else {
                        membersResourceList = ArrayList()
                    }
                } else {
                    membersResourceList = ArrayList()
                }

                viewModel.setEnumarationIdOffline(householdId)


            } else if (it?.status == Status.ERROR) {
                val errorDialogFragment = ErrorDialogFragment()
                errorDialogFragment.setErrorMessage("The Paticipant ID is not found")
                errorDialogFragment.show(fragmentManager!!)
            }
            binding.executePendingBindings()

        })

        viewModel.houseHoldBodyOffline?.observe(this, Observer { membersResource ->
            if (membersResource?.status == Status.SUCCESS) {
                household = membersResource.data?.householdRequest
            }
        })

        viewModel.participantMetas?.observe(this, Observer { userData ->
            if (userData?.data != null) {
                // setupNavigationDrawer(userData.data)
//                user = userData.data
//
//                meta?.registeredBy = user?.id
            }

        })

        binding.buttonSubmit.singleClick {
            //            var bundle = bundleOf("member" to member, "householdId" to householdId)
            binding.root.hideKeyboard()
            if (!isCheckListNotCompleted()) {
                if (validateContinue()) {
                    findNavController().navigate(
                        R.id.action_checkListFragment_to_explanationFragment_sg,
                        bundleOf(
                            "hours_fasted" to binding.durationEditText.text.toString(),
                            "meta" to meta,
                            "household" to household,
                            "householdId" to householdId)
                    )
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.registration_preregistration_nid_fail),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.registration_preregistration_check_list_complete_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.radioGroupAbove.setOnCheckedChangeListener({ radioGroup, i ->
            if (radioGroup.checkedRadioButtonId == R.id.no) {
                binding.radioGroupAboveValue = true
                radioButtonDisable(binding.yesresident, binding.noresident, null, textViewResident)
                radioButtonDisable(binding.yesfast, binding.nofast, null, textViewFast)
                radioButtonDisable(binding.yesNational, binding.noNational, null, textViewNationalID)
                radioButtonDisable(
                    binding.yesmedications,
                    binding.nomedications,
                    binding.notApplicableMedications,
                    textViewMedications
                )


            } else {
                binding.radioGroupAboveValue = false
                radioButtonEnable(binding.yesresident, binding.noresident, null, textViewResident)
                radioButtonEnable(binding.yesfast, binding.nofast, null, textViewFast)
                radioButtonEnable(binding.yesNational, binding.noNational, null, textViewNationalID)
                radioButtonEnable(
                    binding.yesmedications,
                    binding.nomedications,
                    binding.notApplicableMedications,
                    textViewMedications
                )

            }
            binding.executePendingBindings()
        })

        binding.radioGroupResident.setOnCheckedChangeListener({ radioGroup, i ->
            if (radioGroup.checkedRadioButtonId == R.id.noresident) {
                binding.radioGroupResidentValue = true
                radioButtonDisable(binding.yes, binding.no, null, textViewAbove)
                radioButtonDisable(binding.yesfast, binding.nofast, null, textViewFast)
                radioButtonDisable(binding.yesNational, binding.noNational, null, textViewNationalID)
                radioButtonDisable(
                    binding.yesmedications,
                    binding.nomedications,
                    binding.notApplicableMedications,
                    textViewMedications
                )

            } else {
                binding.radioGroupResidentValue = false
                radioButtonEnable(binding.yes, binding.no, null, textViewAbove)
                radioButtonEnable(binding.yesfast, binding.nofast, null, textViewFast)
                radioButtonEnable(binding.yesNational, binding.noNational, null, textViewNationalID)
                radioButtonEnable(
                    binding.yesmedications,
                    binding.nomedications,
                    binding.notApplicableMedications,
                    textViewMedications
                )
            }
            binding.executePendingBindings()
        })

        binding.radioGroupFast.setOnCheckedChangeListener({ radioGroup, i ->
            if (radioGroup.checkedRadioButtonId == R.id.nofast) {
                binding.radioGroupFastValue = true
                binding.radioGroupFastDurationValue = false

                radioButtonDisable(binding.yes, binding.no, null, textViewAbove)
                radioButtonDisable(binding.yesresident, binding.noresident, null, textViewResident)
                radioButtonDisable(binding.yesNational, binding.noNational, null, textViewNationalID)
                radioButtonDisable(
                    binding.yesmedications,
                    binding.nomedications,
                    binding.notApplicableMedications,
                    textViewMedications
                )

            } else if (radioGroup.checkedRadioButtonId == R.id.yesfast) {

                binding.radioGroupFastDurationValue = true
                binding.radioGroupFastValue = false

                radioButtonEnable(binding.yes, binding.no, null, textViewAbove)
                radioButtonEnable(binding.yesresident, binding.noresident, null, textViewResident)
                radioButtonEnable(binding.yesNational, binding.noNational, null, textViewNationalID)
                radioButtonEnable(
                    binding.yesmedications,
                    binding.nomedications,
                    binding.notApplicableMedications,
                    textViewMedications
                )

            } else {
                binding.radioGroupFastValue = false
            }
            binding.executePendingBindings()
        })

        binding.radioGroupNationalID.setOnCheckedChangeListener({ radioGroup, i ->
            binding.root.hideKeyboard()
            if (radioGroup.checkedRadioButtonId == R.id.noNational) {
                binding.radioGroupNationalIDValue = true

                radioButtonDisable(binding.yes, binding.no, null, textViewAbove)
                radioButtonDisable(binding.yesresident, binding.noresident, null, textViewResident)
                radioButtonDisable(binding.yesfast, binding.nofast, null, textViewFast)
                radioButtonDisable(
                    binding.yesmedications,
                    binding.nomedications,
                    binding.notApplicableMedications,
                    textViewMedications
                )

            } else {
                binding.radioGroupNationalIDValue = false

                radioButtonEnable(binding.yes, binding.no, null, textViewAbove)
                radioButtonEnable(binding.yesresident, binding.noresident, null, textViewResident)
                radioButtonEnable(binding.yesfast, binding.nofast, null, textViewFast)
                radioButtonEnable(
                    binding.yesmedications,
                    binding.nomedications,
                    binding.notApplicableMedications,
                    textViewMedications
                )
            }
            binding.executePendingBindings()
        })

        binding.radioGroupMedications.setOnCheckedChangeListener({ radioGroup, i ->
            if (radioGroup.checkedRadioButtonId == R.id.nomedications) {
                binding.radioGroupMedicationsValue = true

                radioButtonDisable(binding.yes, binding.no, null, textViewAbove)
                radioButtonDisable(binding.yesresident, binding.noresident, null, textViewResident)
                radioButtonDisable(binding.yesfast, binding.nofast, null, textViewFast)
                radioButtonDisable(binding.yesNational, binding.noNational, null, textViewNationalID)

            } else {
                binding.radioGroupMedicationsValue = false

                radioButtonEnable(binding.yes, binding.no, null, textViewAbove)
                radioButtonEnable(binding.yesresident, binding.noresident, null, textViewResident)
                radioButtonEnable(binding.yesfast, binding.nofast, null, textViewFast)
                radioButtonEnable(binding.yesNational, binding.noNational, null, textViewNationalID)

            }
            binding.executePendingBindings()
        })

        binding.durationEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length > 0) {
                    validateFasted(s)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })


        binding.buttonBackToHomeOne.singleClick {
            val appCompatActivity = requireActivity() as AppCompatActivity
            appCompatActivity.finish()
        }
        binding.buttonBackToHomeTwo.singleClick {
            val appCompatActivity = requireActivity() as AppCompatActivity
            appCompatActivity.finish()
        }
        binding.buttonBackToHomeThree.singleClick {
            val appCompatActivity = requireActivity() as AppCompatActivity
            appCompatActivity.finish()
        }
        binding.buttonBackToHomeFour.singleClick {
            val appCompatActivity = requireActivity() as AppCompatActivity
            appCompatActivity.finish()
        }
        binding.buttonBackToHomeFive.singleClick {
            val appCompatActivity = requireActivity() as AppCompatActivity
            appCompatActivity.finish()
        }
        binding.buttonBackToHomeSix.singleClick {
            val appCompatActivity = requireActivity() as AppCompatActivity
            appCompatActivity.finish()
        }

    }

    fun radioButtonDisable(
        radioYesButton: RadioButton,
        radioNoButton: RadioButton,
        radioNoApplicableButton: RadioButton?,
        textView: TextView
    ) {
        radioYesButton.setTextColor(resources.getColor(R.color.gray))
        radioNoButton.setTextColor(resources.getColor(R.color.gray))
        radioYesButton.isEnabled = false
        radioNoButton.isEnabled = false

        textView.setTextColor(resources.getColor(R.color.gray))

        radioNoApplicableButton?.setTextColor(resources.getColor(R.color.gray))
        radioNoApplicableButton?.isEnabled = false

        binding.buttonSubmit.setBackgroundResource(R.drawable.ic_button_disable_primary)
        binding.buttonSubmit.isEnabled = false
    }

    fun radioButtonEnable(
        radioYesButton: RadioButton,
        radioNoButton: RadioButton,
        radioNoApplicableButton: RadioButton?,
        textView: TextView
    ) {
        radioYesButton.setTextColor(resources.getColor(R.color.primary_material_dark))
        radioNoButton.setTextColor(resources.getColor(R.color.primary_material_dark))
        radioYesButton.isEnabled = true
        radioNoButton.isEnabled = true

        radioNoApplicableButton?.setTextColor(resources.getColor(R.color.primary_material_dark))
        radioNoApplicableButton?.isEnabled = true

        textView.setTextColor(resources.getColor(R.color.primary_material_dark))

        binding.buttonSubmit.setBackgroundResource(R.drawable.ic_button_fill_primary)
        binding.buttonSubmit.isEnabled = true
    }

    fun validateFasted(s: CharSequence?) {
        if (s.toString().toInt() < 8) {

            binding.radioGroupFastDurationMinValue = true

            radioButtonDisable(binding.yes, binding.no, null, textViewAbove)
            radioButtonDisable(binding.yesresident, binding.noresident, null, textViewResident)
            radioButtonDisable(
                binding.yesmedications,
                binding.nomedications,
                binding.notApplicableMedications,
                textViewMedications
            )
            radioButtonDisable(binding.yesNational, binding.noNational, null, textViewNationalID)

        } else {

            binding.radioGroupFastDurationMinValue = false

            radioButtonEnable(binding.yes, binding.no, null, textViewAbove)
            radioButtonEnable(binding.yesresident, binding.noresident, null, textViewResident)
            radioButtonEnable(
                binding.yesmedications,
                binding.nomedications,
                binding.notApplicableMedications,
                textViewMedications
            )
            radioButtonEnable(binding.yesNational, binding.noNational, null, textViewNationalID)


        }
    }

    private fun validateContinue(): Boolean {
        return !binding.radioGroupAboveValue!! &&
                !binding.radioGroupResidentValue!! &&
                binding.radioGroupFastDurationValue!! &&
                !binding.radioGroupMedicationsValue!! &&
                !binding.radioGroupFastDurationMinValue!!
    }

    private fun isCheckListNotCompleted(): Boolean {
        return binding.radioGroupAboveValue == null ||
                binding.radioGroupResidentValue == null ||
                binding.radioGroupFastDurationValue == null ||
                binding.radioGroupMedicationsValue == null ||
                binding.radioGroupFastDurationMinValue == null


    }

    // to set the 24 hours time ------------------------------ 7.2.2020 --------- Nuwan ----------

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

    // -------------------------------------------------------------------------------------------

    override fun onPause() {
        super.onPause()

        viewModel.houseHoldBody?.removeObservers(this)
    }


    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}

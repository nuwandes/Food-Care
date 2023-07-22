package org.southasia.foodcare.ui.participantlist.attendance.consent

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import io.reactivex.disposables.CompositeDisposable
import org.southasia.foodcare.MeasurementListActivity
import org.southasia.foodcare.R
import org.southasia.foodcare.binding.FragmentDataBindingComponent
import org.southasia.foodcare.databinding.ConsentFragmentBinding
import org.southasia.foodcare.db.MemberTypeConverters.gson
import org.southasia.foodcare.di.Injectable
import org.southasia.foodcare.event.BitmapRxBus
import org.southasia.foodcare.ui.participantlist.attendance.consent.reasondialog.ConsentReasonDialogFragment
import org.southasia.foodcare.util.*
import org.southasia.foodcare.vo.ParticipantListItem
import org.southasia.foodcare.vo.SavedBitMap
import java.util.*
import javax.inject.Inject


class ConsentFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    var binding by autoCleared<ConsentFragmentBinding>()

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    private val disposables = CompositeDisposable()

    private var savedBitmap: SavedBitMap? = null

    private var participant: ParticipantListItem? = null

    var prefs : SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            participant = arguments!!.getParcelable<ParticipantListItem>("participant")!!
        } catch (e: KotlinNullPointerException) {
            print(e)
        }

        disposables.add(
            BitmapRxBus.getInstance().toObservable()
                .subscribe({ result ->
                    Log.d("Result", "Member ${result}")
                    savedBitmap = result
                    Log.d("Saved path", result.bitmapPath)
                }, { error ->
                    error.printStackTrace()
                })
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<ConsentFragmentBinding>(
            inflater,
            R.layout.consent_fragment,
            container,
            false
        )
        binding = dataBinding
        setHasOptionsMenu(true)
        val appCompatActivity = requireActivity() as AppCompatActivity
        appCompatActivity.setSupportActionBar(binding.detailToolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        prefs = PreferenceManager.getDefaultSharedPreferences(context)
//
//        val json : String? = prefs?.getString("single_participant","")
//        participant = MemberTypeConverters.gson.fromJson<ParticipantListItem>(json.toString())
//        Log.d("PARTICIPANT_ATTENDANCE", " DATA: " + participant!!.participant_id)
//
        val dob_year: String = participant!!.dob!!.substring(0,4)
        val dob_month: String = participant!!.dob!!.substring(5,7)
        val dob_date : String = participant!!.dob!!.substring(8,10)

        val participantAge: String = getAge(dob_year.toInt(), dob_month.toInt(), dob_date.toInt())

        Log.d("PARTICIPANT_ATTENDANCE", "year: " + dob_year + ", month: " + dob_month + ", date: " + dob_date + "AGE: " + participantAge)

        binding.participantDetails.setText(participant!!.firstname + " " + participant!!.last_name+ ", " + participant!!.gender + ", " + participantAge + " Years, "  + participant!!.participant_id)


        binding.buttonAcceptAndContinue.singleClick {
//            if (savedBitmap != null && savedBitmap1 != null) {
            if (savedBitmap != null) {

                val json1: String = gson.toJson(participant)
                prefs?.edit()?.putString("selected_participant", json1)?.apply()
                val intent = Intent(activity, MeasurementListActivity::class.java)
                startActivity(intent)

//                navController().navigate(
//                    R.id.action_global_BasicDetailsFragment,
//                    bundleOf(
//                        "concentPhotoPath" to savedBitmap?.bitmapPath
//                    )
//                )

            } else {
                activity!!.showToast(getString(R.string.please_take_image))
            }
        }

        binding.saveAndExitButton.singleClick {
            val mDeleteFragmentDialog = ConsentReasonDialogFragment()
            mDeleteFragmentDialog.show(fragmentManager!!)
        }


        if (savedBitmap != null) {
            val rotationDegrees: Float? = savedBitmap?.bitmap?.rotationDegrees?.toFloat()
            binding.userprofile.setRotation(-rotationDegrees!!);
            binding.userprofile.setImageBitmap(savedBitmap?.bitmap?.bitmap)
            binding.cameraButton.visibility = View.INVISIBLE
            binding.profileView.visibility = View.VISIBLE
            binding.executePendingBindings()
        } else {
            binding.profileView.visibility = View.INVISIBLE
            binding.cameraButton.visibility = View.VISIBLE
        }

        binding.cameraButton.singleClick {
            binding.root.hideKeyboard()
            navController().navigate(R.id.action_global_cameraFragment)

        }

        binding.retakeBtn.singleClick {
            binding.root.hideKeyboard()
            savedBitmap?.bitmapPath = ""
            // participantMeta.body.identityImage = ""
            binding.userprofile.setImageBitmap(null)
            binding.cameraButton.visibility = View.VISIBLE
            binding.profileView.visibility = View.INVISIBLE
            // validateNextButton()

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                return navController().navigateUp()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getAge(year: Int, month: Int, date: Int) : String
    {
        val dob : Calendar = Calendar.getInstance()
        val today : Calendar = Calendar.getInstance()

        dob.set(year, month, date)

        var age : Int = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
        {
            age--
        }

        val ageInt : Int = age
        val ageString : String = ageInt.toString()

        return ageString
    }


    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}

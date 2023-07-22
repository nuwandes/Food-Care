package org.southasia.foodcare.ui.bodymeasurements.review.completed

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.R
import org.southasia.foodcare.databinding.BpItemBinding
import org.southasia.foodcare.ui.common.DataBoundListAdapter
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.BloodPressure


class BloodPressureAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((BloodPressure) -> Unit)?
) : DataBoundListAdapter<BloodPressure, BpItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<BloodPressure>() {
        override fun areItemsTheSame(oldItem: BloodPressure, newItem: BloodPressure): Boolean {
            return oldItem.systolic == newItem.systolic
        }

        override fun areContentsTheSame(oldItem: BloodPressure, newItem: BloodPressure): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.diastolic == newItem.diastolic

        }

    }
) {

    override fun createBinding(parent: ViewGroup): BpItemBinding {
        val binding = DataBindingUtil
            .inflate<BpItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.bp_item,
                parent,
                false,
                dataBindingComponent
            )
        binding.root.singleClick {
            binding.bloodPressure?.let {
                callback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: BpItemBinding, item: BloodPressure) {
        binding.bloodPressure = item
        if (item.diastolic.value!!.toInt() > 120) {
            binding.textViewDiastolic.setTextColor(Color.parseColor("#d34836"))
        } else {
            binding.textViewDiastolic.setTextColor(Color.parseColor("#2a2a2a"))
        }
        if (item.systolic.value!!.toInt() > 180) {
            binding.textViewSystolic.setTextColor(Color.parseColor("#d34836"))
        } else {
            binding.textViewSystolic.setTextColor(Color.parseColor("#2a2a2a"))
        }

    }
}

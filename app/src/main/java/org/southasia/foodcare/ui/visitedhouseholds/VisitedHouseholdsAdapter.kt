package org.southasia.foodcare.ui.visitedhouseholds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.R
import org.southasia.foodcare.databinding.HouseHoldItemBinding
import org.southasia.foodcare.ui.common.DataBoundListAdapter
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.request.HouseholdRequest


class VisitedHouseholdRequestAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((HouseholdRequest) -> Unit)?
) : DataBoundListAdapter<HouseholdRequest, HouseHoldItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<HouseholdRequest>() {
        override fun areItemsTheSame(oldItem: HouseholdRequest, newItem: HouseholdRequest): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HouseholdRequest, newItem: HouseholdRequest): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.timestamp == newItem.timestamp
        }
    }
) {

    override fun createBinding(parent: ViewGroup): HouseHoldItemBinding {
        val binding = DataBindingUtil
            .inflate<HouseHoldItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.house_hold_item,
                parent,
                false,
                dataBindingComponent
            )
        binding.root.singleClick {
            binding.household?.let {
                callback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: HouseHoldItemBinding, item: HouseholdRequest) {
        binding.household = item
    }
}

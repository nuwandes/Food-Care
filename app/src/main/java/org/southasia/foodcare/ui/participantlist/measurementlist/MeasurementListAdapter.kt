package org.southasia.foodcare.ui.participantlist.measurementlist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.R
import org.southasia.foodcare.databinding.MeasurementListItemBinding
import org.southasia.foodcare.databinding.ParticipantListItemBinding
import org.southasia.foodcare.ui.common.DataBoundListAdapter
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.*

class MeasurementListAdapter (
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((MeasurementListItem) -> Unit)?
) : DataBoundListAdapter<MeasurementListItem, MeasurementListItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<MeasurementListItem>() {
        override fun areItemsTheSame(oldItem: MeasurementListItem, newItem: MeasurementListItem): Boolean {
            return oldItem.id == newItem.id


        }

        override fun areContentsTheSame(oldItem: MeasurementListItem, newItem: MeasurementListItem): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.station_name == newItem.station_name
        }
    }
) {

    override fun createBinding(parent: ViewGroup): MeasurementListItemBinding {
        val binding = DataBindingUtil
            .inflate<MeasurementListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.measurement_list_item,
                parent,
                false,
                dataBindingComponent
            )
        binding.root.singleClick {
            binding.measurementListItem?.let {
                callback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: MeasurementListItemBinding, item: MeasurementListItem) {
        binding.measurementListItem = item

        val greenColor: String = "#388e3c"
        val redColor: String = "#d34836"

        if (item.status == "Completed")
        {
            binding.participantId.setTextColor(Color.parseColor(greenColor))
        }
        else
        {
            binding.participantId.setTextColor(Color.parseColor(redColor))
        }

        if (item.station_name == "Consent")
        {
            binding.buttonArrow.visibility = View.GONE
        }

    }

}
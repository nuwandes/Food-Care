package org.southasia.foodcare.ui.samplemanagement.pendingsamplelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.R
import org.southasia.foodcare.databinding.SampleItemBinding
import org.southasia.foodcare.ui.common.DataBoundListAdapter
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.request.SampleRequest


class PendingSampleListAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((SampleRequest) -> Unit)?
) : DataBoundListAdapter<SampleRequest, SampleItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<SampleRequest>() {
        override fun areItemsTheSame(oldItem: SampleRequest, newItem: SampleRequest): Boolean {
            return oldItem.sampleId == newItem.sampleId
        }

        override fun areContentsTheSame(oldItem: SampleRequest, newItem: SampleRequest): Boolean {
            return oldItem.sampleId == newItem.sampleId
                    && oldItem.timestamp == newItem.timestamp
        }
    }
) {

    override fun createBinding(parent: ViewGroup): SampleItemBinding {
        val binding = DataBindingUtil
            .inflate<SampleItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.sample_item,
                parent,
                false,
                dataBindingComponent
            )
        binding.root.singleClick { it ->
            binding.sample?.let {
                callback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: SampleItemBinding, item: SampleRequest) {
        binding.sample = item
    }
}

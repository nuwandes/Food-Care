package org.southasia.foodcare.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.R
import org.southasia.foodcare.databinding.NghruItemBinding
import org.southasia.foodcare.ui.common.DataBoundListAdapter
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.HomeItem


class HomeAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((HomeItem) -> Unit)?
) : DataBoundListAdapter<HomeItem, NghruItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<HomeItem>() {
        override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
        }
    }
) {

    override fun createBinding(parent: ViewGroup): NghruItemBinding {
        val binding = DataBindingUtil
            .inflate<NghruItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.nghru_item,
                parent,
                false,
                dataBindingComponent
            )
        binding.root.singleClick {
            binding.homeItem?.let {
                callback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: NghruItemBinding, item: HomeItem) {
        binding.homeItem = item
    }
}

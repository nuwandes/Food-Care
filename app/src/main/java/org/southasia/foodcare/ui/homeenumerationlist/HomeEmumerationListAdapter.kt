package org.southasia.foodcare.ui.homeenumerationlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.R
import org.southasia.foodcare.databinding.HomeEmumerationListItemBinding
import org.southasia.foodcare.ui.common.DataBoundListAdapter
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.HomeEmumerationListItem


class HomeEmumerationListAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((HomeEmumerationListItem) -> Unit)?
) : DataBoundListAdapter<HomeEmumerationListItem, HomeEmumerationListItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<HomeEmumerationListItem>() {
        override fun areItemsTheSame(oldItem: HomeEmumerationListItem, newItem: HomeEmumerationListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeEmumerationListItem, newItem: HomeEmumerationListItem): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
        }
    }
) {

    override fun createBinding(parent: ViewGroup): HomeEmumerationListItemBinding {
        val binding = DataBindingUtil
            .inflate<HomeEmumerationListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.home_emumeration_list_item,
                parent,
                false,
                dataBindingComponent
            )
        binding.root.singleClick {
            binding.homeEmumerationListItem?.let {
                callback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: HomeEmumerationListItemBinding, item: HomeEmumerationListItem) {
        binding.homeEmumerationListItem = item
    }
}

package org.southasia.foodcare.ui.enumeration.householdmembers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.R
import org.southasia.foodcare.databinding.MemberItemBinding
import org.southasia.foodcare.ui.common.DataBoundListAdapter
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.request.Member


class HouseholdMembersAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((Member) -> Unit)?
) : DataBoundListAdapter<Member, MemberItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Member>() {
        override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.timestamp == newItem.timestamp
        }
    }
) {

    var i = 1

    override fun createBinding(parent: ViewGroup): MemberItemBinding {

        val binding = DataBindingUtil
            .inflate<MemberItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.member_item,
                parent,
                false,
                dataBindingComponent
            )
        binding.root.singleClick {
            binding.member?.let {
                callback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: MemberItemBinding, item: Member) {
        binding.member = item
        binding.count = i.toString()
        i++

    }
}

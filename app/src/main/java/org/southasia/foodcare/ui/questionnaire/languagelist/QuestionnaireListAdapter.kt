package org.southasia.foodcare.ui.questionnaire.languagelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import org.southasia.foodcare.AppExecutors
import org.southasia.foodcare.R
import org.southasia.foodcare.databinding.QuestionnaireItemBinding
import org.southasia.foodcare.ui.common.DataBoundListAdapter
import org.southasia.foodcare.util.singleClick
import org.southasia.foodcare.vo.Questionnaire


class QuestionnaireListAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((Questionnaire) -> Unit)?
) : DataBoundListAdapter<Questionnaire, QuestionnaireItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Questionnaire>() {
        override fun areItemsTheSame(oldItem: Questionnaire, newItem: Questionnaire): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Questionnaire, newItem: Questionnaire): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.language == newItem.language
        }
    }
) {

    override fun createBinding(parent: ViewGroup): QuestionnaireItemBinding {
        val binding = DataBindingUtil
            .inflate<QuestionnaireItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.questionnaire_item,
                parent,
                false,
                dataBindingComponent
            )
        binding.root.singleClick { it ->
            binding.questionnaire?.let {
                callback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: QuestionnaireItemBinding, item: Questionnaire) {
        binding.questionnaire = item
    }
}

package org.southasia.foodcare.ui.datamanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import org.southasia.foodcare.R


class DataManagementAdapter(private val records: ArrayList<Any>?) : RecyclerView.Adapter<DataManagementHolder>() {

    override fun onBindViewHolder(holder: DataManagementHolder, position: Int) {

        if (records != null) {
            val record = records[position]
            holder.bindRecord(record,position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataManagementHolder {

        val inflatedView = parent.inflate(R.layout.data_management_item, false)
        return DataManagementHolder(inflatedView)
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount(): Int {

        if (records != null)
            return records.size
        else
            return 0
    }

}
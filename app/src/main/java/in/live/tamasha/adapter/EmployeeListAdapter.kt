package `in`.live.tamasha.adapter

import `in`.live.tamasha.Utility
import `in`.live.tamasha.R
import `in`.live.tamasha.models.EmployeeModel
import `in`.live.tamasha.viewholder.EmployeeViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView

class EmployeeListAdapter : RecyclerView.Adapter<EmployeeViewHolder>() {
    private var asyncListDiffer = AsyncListDiffer(this, Utility().diffUtil)

    internal fun submitList(searchList: ArrayList<EmployeeModel.Data>) {
        asyncListDiffer.submitList(searchList.map {
            it.copy()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee_list_view_item, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }


    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val value = asyncListDiffer.currentList[position]
        holder.bindData(value)
        holder.itemView.setOnClickListener {
            System.out.println("value-->${value.isExpanded}")
            if (value.isExpanded == false) {
                value.isExpanded = true
            } else if (value.isExpanded == true) {
                value.isExpanded = false
            }
            notifyItemChanged(position)
        }
    }
}
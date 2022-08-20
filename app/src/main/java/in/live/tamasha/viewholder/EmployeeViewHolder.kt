package `in`.live.tamasha.viewholder

import `in`.live.tamasha.R
import `in`.live.tamasha.makeViewGone
import `in`.live.tamasha.makeViewVisible
import `in`.live.tamasha.models.EmployeeModel
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textView: TextView by lazy { view.findViewById(R.id.employeeNameTextView) }
    private val imageView: ImageView by lazy { view.findViewById(R.id.employeeProfileImage) }
    private val empSalaryTextView: TextView by lazy { view.findViewById(R.id.empSalary) }

    internal fun bindData(value: EmployeeModel.Data?) {
        if (value == null) {
            return
        }
        val empSalaryStringBuilder = StringBuilder()
        empSalaryStringBuilder.append("Emp Salary:-").append(value.employeeSalary)

        textView.text = value.employeeName
        empSalaryTextView.text = empSalaryStringBuilder

        val imageUrl = value.profileImage
        if (!imageUrl.isNullOrEmpty()) {
            Picasso.get().load(imageUrl).into(imageView)
        }
        if (value.isExpanded) {
            empSalaryTextView.makeViewVisible()
        }else{
            empSalaryTextView.makeViewGone()
        }

    }
}
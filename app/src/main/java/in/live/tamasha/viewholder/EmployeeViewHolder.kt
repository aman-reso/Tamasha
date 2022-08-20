package `in`.live.tamasha.viewholder

import `in`.live.tamasha.R
import `in`.live.tamasha.models.EmployeeModel
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var textView: TextView = view.findViewById(R.id.employeeNameTextView)
    private var imageView: ImageView = view.findViewById(R.id.employeeProfileImage)

    internal fun bindData(value: EmployeeModel.Data?) {
        if (value == null) {
            return
        }
        textView.text = value.employeeName
        val imageUrl = value.profileImage
        if (!imageUrl.isNullOrEmpty()) {
            Picasso.get().load(imageUrl).into(imageView)
        }
    }
}
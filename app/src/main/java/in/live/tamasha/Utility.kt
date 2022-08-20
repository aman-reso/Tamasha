package `in`.live.tamasha

import `in`.live.tamasha.constants.AppConstants
import `in`.live.tamasha.models.EmployeeModel
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.Gson
import java.lang.ClassCastException
import javax.inject.Inject

val gson = Gson()

class Utility @Inject constructor(){
    internal var diffUtil = object : DiffUtil.ItemCallback<EmployeeModel.Data>() {
        override fun areItemsTheSame(oldItem: EmployeeModel.Data, newItem: EmployeeModel.Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EmployeeModel.Data, newItem: EmployeeModel.Data): Boolean {
            return oldItem == newItem
        }

    }

   suspend fun getDefaultOfflineResponse(): EmployeeModel? {
        return try {
            gson.fromJson(AppConstants.dummyResponse, EmployeeModel::class.java)
        } catch (e: ClassCastException) {
            null
        }
    }
    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}
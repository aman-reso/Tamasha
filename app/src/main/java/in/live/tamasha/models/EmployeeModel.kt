package `in`.live.tamasha.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EmployeeModel(
    @Expose(serialize = true, deserialize = true)
    @SerializedName("data")
    var `data`: ArrayList<Data>? = null,
    @Expose(serialize = true, deserialize = true)
    @SerializedName("status")
    var status: String? = null // success
) {
    data class Data(
        @SerializedName("employee_age")
        @Expose(serialize = true, deserialize = true)
        var employeeAge: Int, // 61
        @Expose(serialize = true, deserialize = true)
        @SerializedName("employee_name")
        var employeeName: String, // Tiger Nixon
        @Expose(serialize = true, deserialize = true)
        @SerializedName("employee_salary")
        var employeeSalary: Int, // 320800
        @SerializedName("id")
        @Expose(serialize = true, deserialize = true)
        var id: Int, // 1
        @SerializedName("profile_image")
        @Expose(serialize = true, deserialize = true)
        var profileImage: String? = null,

        @Transient
        var isExpanded: Boolean = false
    )
}
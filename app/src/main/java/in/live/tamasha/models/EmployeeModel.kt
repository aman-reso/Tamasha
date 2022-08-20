package `in`.live.tamasha.models


import com.google.gson.annotations.SerializedName

data class EmployeeModel(
    var `data`: ArrayList<Data>? = null,
    var status: String?=null // success
) {
    data class Data(
        @SerializedName("employee_age")
        var employeeAge: Int, // 61
        @SerializedName("employee_name")
        var employeeName: String, // Tiger Nixon
        @SerializedName("employee_salary")
        var employeeSalary: Int, // 320800
        var id: Int, // 1
        @SerializedName("profile_image")
        var profileImage: String?=null
    )
}
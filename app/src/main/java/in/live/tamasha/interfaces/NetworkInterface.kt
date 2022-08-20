package `in`.live.tamasha.interfaces

import `in`.live.tamasha.models.EmployeeModel
import retrofit2.http.GET

interface NetworkInterface {

    @GET("/v1/61cf7d91-a7f8-405e-b505-67926b759d78")
    suspend fun getAllEmployeeDataFromServer(): EmployeeModel
}


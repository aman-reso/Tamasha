package `in`.live.tamasha.repository

import `in`.live.tamasha.apimodule.safeApiCall
import `in`.live.tamasha.interfaces.NetworkInterface
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val apiService: NetworkInterface) {

    suspend fun getEmployeeListFromServer() = safeApiCall {
        apiService.getAllEmployeeDataFromServer()
    }
}
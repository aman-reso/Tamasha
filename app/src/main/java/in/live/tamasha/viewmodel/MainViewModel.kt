package `in`.live.tamasha.viewmodel

import `in`.live.tamasha.Utility
import `in`.live.tamasha.models.GeneralNetworkResponse
import `in`.live.tamasha.models.GlobalNetResponse
import `in`.live.tamasha.repository.NetworkRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

val io_dispatcher = Dispatchers.IO

@HiltViewModel
class MainViewModel @Inject constructor(var repository: NetworkRepository) : ViewModel() {
    internal var employeeResponseLiveData = MutableLiveData<GeneralNetworkResponse>()

    @Inject
    lateinit var utility: Utility
    internal fun getEmployeeListFromServer() = viewModelScope.launch(io_dispatcher) {
        when (val networkResponse = repository.getEmployeeListFromServer()) {
            is GlobalNetResponse.Failure -> {
                getDummyResponse()
            }
            is GlobalNetResponse.Success -> {
                val generalSuccessResponse = GeneralNetworkResponse.Success(networkResponse.value)
                employeeResponseLiveData.postValue(generalSuccessResponse)
            }
        }
    }

    private suspend fun getDummyResponse() {
        val localResponse = utility.getDefaultOfflineResponse()
        if (localResponse == null) {
            val generalErrorResponse = GeneralNetworkResponse.ErrorMessage("Something went wrong")
            employeeResponseLiveData.postValue(generalErrorResponse)
        } else {
            val generalSuccessResponse = GeneralNetworkResponse.Success(localResponse)
            employeeResponseLiveData.postValue(generalSuccessResponse)
        }
    }
}
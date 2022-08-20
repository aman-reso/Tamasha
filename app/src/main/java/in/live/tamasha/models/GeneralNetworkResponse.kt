package `in`.live.tamasha.models

sealed class GeneralNetworkResponse {
    class Success<T>(var successResponse: T) : GeneralNetworkResponse()
    class ErrorMessage(var value: String?) : GeneralNetworkResponse()
}
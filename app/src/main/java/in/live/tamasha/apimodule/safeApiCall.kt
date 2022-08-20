package `in`.live.tamasha.apimodule

import `in`.live.tamasha.models.GlobalNetResponse
import `in`.live.tamasha.viewmodel.io_dispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): GlobalNetResponse<T> {
    return withContext(io_dispatcher) {
        try {
            GlobalNetResponse.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    val message = throwable.localizedMessage
                    GlobalNetResponse.Failure(message)
                }
                else -> {
                    val message = throwable.localizedMessage
                    GlobalNetResponse.Failure(message)
                }
            }
        }
    }
}

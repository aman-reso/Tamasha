package `in`.live.tamasha.models

sealed class GlobalNetResponse<T> {
    data class Success<T>(var value:T) : GlobalNetResponse<T>()
    data class Failure<T>(var error: String) : GlobalNetResponse<T>()
}
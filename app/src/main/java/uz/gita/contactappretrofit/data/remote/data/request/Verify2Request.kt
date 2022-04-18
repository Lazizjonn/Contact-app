package uz.gita.contactappretrofit.data.remote.data.request

data class Verify2Request (
        val phone: String,
        val code: String,
    )
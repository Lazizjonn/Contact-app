package uz.gita.contactappretrofit.data.model.request

data class Verify2Request (
        val phone: String,
        val code: String,
    )
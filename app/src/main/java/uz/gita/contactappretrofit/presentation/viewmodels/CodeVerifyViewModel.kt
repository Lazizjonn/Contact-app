package uz.gita.contactappretrofit.presentation.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.contactappretrofit.data.model.request.Verify2Request

interface CodeVerifyViewModel {

    val progressLiveData : LiveData<Boolean>
    val notConnectionLiveData : LiveData<Unit>
    val openContactScreenLiveData : LiveData<Unit>
    val errorLiveData : LiveData<String>

    fun checkCode(data: Verify2Request)
}


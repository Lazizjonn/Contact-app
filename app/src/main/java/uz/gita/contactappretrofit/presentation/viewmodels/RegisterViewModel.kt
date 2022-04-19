package uz.gita.contactappretrofit.presentation.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.contactappretrofit.data.model.request.Register2Request

interface RegisterViewModel {

    val progressLiveData : LiveData<Boolean>
    val notConnectionLiveData : LiveData<Unit>
    val openVerifyScreenLiveData : LiveData<Unit>
    val errorLiveData : LiveData<String>

    fun openVerifyScreen(data : Register2Request)

}
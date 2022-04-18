package uz.gita.contactappretrofit.presentation.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.contactappretrofit.data.remote.data.request.Login2Request
import uz.gita.contactappretrofit.data.remote.data.request.Register2Request

interface RegisterViewModel {

    // Events
    val progressLiveData : LiveData<Boolean>
    val notConnectionLiveData : LiveData<Unit>
    val openVerifyScreenLiveData : LiveData<Unit>
    val errorLiveData : LiveData<String>


    // Actions
    fun openVerifyScreen(data : Register2Request)

}
package uz.gita.contactappretrofit.presentation.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.contactappretrofit.data.remote.data.request.Login2Request


interface LoginViewModel {

    val progressLiveData : LiveData<Boolean>
    val notConnectionLiveData : LiveData<Unit>
    val openRegisterScreenLiveData : LiveData<Unit>
    val openContactScreenLiveData : LiveData<Unit>
    val errorLiveData : LiveData<String>

    fun loginUser(data : Login2Request)
    fun openRegisterScreen()
}




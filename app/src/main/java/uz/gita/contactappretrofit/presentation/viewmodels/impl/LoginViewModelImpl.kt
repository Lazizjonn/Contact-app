package uz.gita.contactappretrofit.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.contactappretrofit.data.source.local.mySharedPref.MySharedPref
import uz.gita.contactappretrofit.data.source.remote.api.ContactApi
import uz.gita.contactappretrofit.data.model.request.Login2Request
import uz.gita.contactappretrofit.presentation.viewmodels.LoginViewModel
import uz.gita.contactappretrofit.utils.isConnected
import uz.gita.contactappretrofit.utils.myEnqueue
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(private val api: ContactApi, private val pref: MySharedPref) : ViewModel(), LoginViewModel {

    override val progressLiveData = MutableLiveData<Boolean>()
    override val notConnectionLiveData = MutableLiveData<Unit>()
    override val openRegisterScreenLiveData = MutableLiveData<Unit>()
    override val openContactScreenLiveData = MutableLiveData<Unit>()
    override val errorLiveData = MutableLiveData<String>()

    override fun loginUser(data: Login2Request) {
        if (!isConnected()) {
            notConnectionLiveData.value = Unit
            return
        }
        progressLiveData.value = true
        api.loginUser(data).myEnqueue(
            // onSuccess
            { response ->
                progressLiveData.value = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        pref.token = it.token
                    }
                    openContactScreenLiveData.value = Unit
                } else {
                    errorLiveData.value = "Error"
                }
            },
            //onFail
            {
                progressLiveData.value = false
                errorLiveData.value = it.message
            })
    }
    override fun openRegisterScreen() {
        openRegisterScreenLiveData.value = Unit
        progressLiveData.value = false
    }
}

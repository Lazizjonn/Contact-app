package uz.gita.contactappretrofit.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.contactappretrofit.data.source.local.mySharedPref.MySharedPref
import uz.gita.contactappretrofit.data.source.remote.api.ContactApi
import uz.gita.contactappretrofit.data.model.request.Verify2Request
import uz.gita.contactappretrofit.presentation.viewmodels.CodeVerifyViewModel
import uz.gita.contactappretrofit.utils.isConnected
import uz.gita.contactappretrofit.utils.myEnqueue
import javax.inject.Inject

@HiltViewModel
class CodeVerifyViewModelImpl @Inject constructor(
    private val api: ContactApi,
    private val pref: MySharedPref) : ViewModel(), CodeVerifyViewModel {

    override val progressLiveData = MutableLiveData<Boolean>()
    override val notConnectionLiveData = MutableLiveData<Unit>()
    override val openContactScreenLiveData = MutableLiveData<Unit>()
    override val errorLiveData = MutableLiveData<String>()

    override fun checkCode(data: Verify2Request) {
        if (!isConnected()) {
            notConnectionLiveData.value = Unit
            return
        }
        progressLiveData.value = true
        api.verifyUser(data).myEnqueue(
            // onSuccess
            { response ->
                progressLiveData.value = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        pref.token = it.token
                        openContactScreenLiveData.value = Unit
                    }

                } else {
                    progressLiveData.value = false
                    errorLiveData.value = "Unsuccessful"
                }
            },
            //onFail
            {
                progressLiveData.value = false
                errorLiveData.value = it.message
            })
    }

}
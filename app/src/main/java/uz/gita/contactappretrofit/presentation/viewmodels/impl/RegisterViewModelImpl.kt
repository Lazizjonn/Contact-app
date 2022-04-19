package uz.gita.contactappretrofit.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.contactappretrofit.data.source.remote.api.ContactApi
import uz.gita.contactappretrofit.data.model.request.Register2Request
import uz.gita.contactappretrofit.presentation.viewmodels.RegisterViewModel
import uz.gita.contactappretrofit.utils.isConnected
import uz.gita.contactappretrofit.utils.myEnqueue
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(private val api: ContactApi) : ViewModel(), RegisterViewModel {

    override val progressLiveData = MutableLiveData<Boolean>()
    override val notConnectionLiveData = MutableLiveData<Unit>()
    override val openVerifyScreenLiveData = MutableLiveData<Unit>()
    override val errorLiveData = MutableLiveData<String>()

    override fun openVerifyScreen(data: Register2Request) {
        if (!isConnected()) {
            notConnectionLiveData.value = Unit
            return
        }
        progressLiveData.value = true
        api.registerUser(data).myEnqueue(
            // onSuccess
            { response ->
                progressLiveData.value = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        openVerifyScreenLiveData.value = Unit
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
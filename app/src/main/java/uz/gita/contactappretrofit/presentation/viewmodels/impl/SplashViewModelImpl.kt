package uz.gita.contactappretrofit.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.contactappretrofit.data.local.MySharedPref
import uz.gita.contactappretrofit.presentation.viewmodels.SplashViewModel
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(private val pref: MySharedPref) : ViewModel(), SplashViewModel {

    override val openLoginScreenLiveData = MutableLiveData<Unit>()
    override val openContactScreenLiveData = MutableLiveData<Unit>()

    init {
        val thread = Executors.newSingleThreadExecutor()
        thread.execute {
            thread.awaitTermination(2, TimeUnit.SECONDS)
            if (pref.token.isNotEmpty()) {
                openContactScreenLiveData.postValue(Unit)
            } else {
                openLoginScreenLiveData.postValue(Unit)
            }
            thread.shutdown()
        }
    }
}
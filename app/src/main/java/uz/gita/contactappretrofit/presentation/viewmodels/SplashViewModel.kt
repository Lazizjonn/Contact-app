package uz.gita.contactappretrofit.presentation.viewmodels

import androidx.lifecycle.LiveData


interface SplashViewModel {

    val openContactScreenLiveData : LiveData<Unit>
    val openLoginScreenLiveData : LiveData<Unit>
}


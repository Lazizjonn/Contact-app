package uz.gita.contactappretrofit.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.contactappretrofit.R
import uz.gita.contactappretrofit.databinding.FragmentSplashScreenBinding
import uz.gita.contactappretrofit.presentation.viewmodels.SplashViewModel
import uz.gita.contactappretrofit.presentation.viewmodels.impl.SplashViewModelImpl
import uz.gita.contactappretrofit.utils.myApply

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.fragment_splash_screen) {

    private val binding by viewBinding(FragmentSplashScreenBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {

        viewModel.openLoginScreenLiveData.observe(viewLifecycleOwner, openLoginScreenObserver)
        viewModel.openContactScreenLiveData.observe(viewLifecycleOwner, openContactScreenObserver)
    }

    private val openLoginScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_loginScreen)
    }
    private val openContactScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_contactScreen)
    }

}
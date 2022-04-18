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
import uz.gita.contactappretrofit.data.remote.data.request.Login2Request
import uz.gita.contactappretrofit.databinding.FragmentLoginScreenBinding
import uz.gita.contactappretrofit.presentation.viewmodels.LoginViewModel
import uz.gita.contactappretrofit.presentation.viewmodels.impl.LoginViewModelImpl
import uz.gita.contactappretrofit.utils.addListener
import uz.gita.contactappretrofit.utils.myApply
import uz.gita.contactappretrofit.utils.showToast
import uz.gita.contactappretrofit.utils.values

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.fragment_login_screen) {

    private val binding by viewBinding(FragmentLoginScreenBinding::bind)
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()

    private var boolPhone = false
    private var boolPassword = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {

        editTextPassword.addListener {
            boolPassword = it.length in 3 until 15
            check()
        }
        editTextPhone.addListener {
            boolPhone = it.length == 13 && it.startsWith("+998")
            check()
        }

        buttonLogin.isEnabled = false
        buttonRegister.setOnClickListener { viewModel.openRegisterScreen() }
        buttonLogin.setOnClickListener {
            viewModel.loginUser(
                Login2Request(
                    editTextPhone.values(),
                    editTextPassword.values()
                )
            )
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.notConnectionLiveData.observe(viewLifecycleOwner, notConnectionObserver)
        viewModel.openContactScreenLiveData.observe(viewLifecycleOwner, openContactScreenObserver)
        viewModel.openRegisterScreenLiveData.observe(viewLifecycleOwner, openRegisterScreenObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)

    }

    private fun check(){
        binding.buttonLogin.isEnabled = boolPassword && boolPhone
    }

    private val errorObserver = Observer<String> { showToast(it) }
    private val openContactScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_loginScreen_to_contactScreen)
    }
    private val openRegisterScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_loginScreen_to_registerScreen)
    }
    private val notConnectionObserver = Observer<Unit> {
        showToast("Sizda mavjud emas")
    }
    private val progressObserver = Observer<Boolean> {
        if (it) binding.progress.show()
        else binding.progress.hide()
    }
}
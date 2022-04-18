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
import uz.gita.contactappretrofit.data.remote.data.request.Register2Request
import uz.gita.contactappretrofit.databinding.FragmentRegisterScreenBinding
import uz.gita.contactappretrofit.presentation.viewmodels.RegisterViewModel
import uz.gita.contactappretrofit.presentation.viewmodels.impl.RegisterViewModelImpl
import uz.gita.contactappretrofit.utils.addListener
import uz.gita.contactappretrofit.utils.myApply
import uz.gita.contactappretrofit.utils.showToast
import uz.gita.contactappretrofit.utils.values

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.fragment_register_screen) {

    private var firstName = false
    private var lastName = false
    private var phoneNumber = false
    private var password = false
    private var confirmPassword = false
    private var data: Register2Request? = null
    private var a = ""
    private val binding by viewBinding(FragmentRegisterScreenBinding::bind)
    private val viewModel: RegisterViewModel by viewModels<RegisterViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {

        inputFirstName.addListener {
            firstName = it.length in 3 until 15
            check()
        }
        inputLastName.addListener {
            lastName = it.length in 3 until 15
            check()
        }
        inputPhoneNumber.addListener {
            phoneNumber = it.length == 13 && it.startsWith("+998")
            check()
        }
        inputPassword.addListener {
            password = (it.length in 3 until 15)
            a = inputPassword.values()
            check()
        }
        inputConfirmPassword.addListener {
            confirmPassword = (it.length in 3 until 15) && (a == it)
            check()
        }

        buttonRegister.isEnabled = false
        buttonRegister.setOnClickListener {
            data = Register2Request(
                inputFirstName.values(), inputLastName.values(),
                inputPhoneNumber.values(), inputPassword.values()
            )
            viewModel.openVerifyScreen(data!!)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.openVerifyScreenLiveData.observe(viewLifecycleOwner, verifyScreenObserver)
        viewModel.notConnectionLiveData.observe(viewLifecycleOwner, notConnectionObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
    }

    private fun check() {
        binding.buttonRegister.isEnabled = firstName && lastName && phoneNumber && password && confirmPassword
    }

    private val errorObserver = Observer<String> { showToast(it) }
    private val verifyScreenObserver = Observer<Unit> {
        findNavController().navigate(RegisterScreenDirections.actionRegisterScreenToVerifyScreen(data))
    }
    private val notConnectionObserver = Observer<Unit> {
        showToast("Sizda mavjud emas")
    }
    private val progressObserver = Observer<Boolean> {
        if (it) binding.progress.show()
        else binding.progress.hide()
    }
}
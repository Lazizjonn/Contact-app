package uz.gita.contactappretrofit.presentation.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.contactappretrofit.R
import uz.gita.contactappretrofit.data.model.request.Verify2Request
import uz.gita.contactappretrofit.databinding.FragmentVerifyScreenBinding
import uz.gita.contactappretrofit.presentation.viewmodels.CodeVerifyViewModel
import uz.gita.contactappretrofit.presentation.viewmodels.impl.CodeVerifyViewModelImpl
import uz.gita.contactappretrofit.utils.myApply
import uz.gita.contactappretrofit.utils.showToast

@AndroidEntryPoint
class VerifyScreen : Fragment(R.layout.fragment_verify_screen) {

    private val binding by viewBinding(FragmentVerifyScreenBinding::bind)
    private val viewModel: CodeVerifyViewModel by viewModels<CodeVerifyViewModelImpl>()
    private var numberList = ArrayList<TextView>()
    private var code = StringBuilder(6)
    private val args: VerifyScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {

        loadViews()

        binding.keyboard.btConfirm.setOnClickListener {
            val data = args.data

            data?.phone?.let { it1 ->
                Verify2Request(
                    it1,
                    code.toString()
                )
            }?.let { it2 ->
                viewModel.checkCode(
                    it2
                )
            }
        }
        binding.keyboard.btClear.setOnClickListener {
            code.deleteCharAt(code.length - 1)
            inputSmsCode.setText(code.toString())
        }
        binding.keyboard.btClear.setOnLongClickListener {
            code.clear()
            inputSmsCode.setText(code.toString())
            return@setOnLongClickListener true
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.openContactScreenLiveData.observe(viewLifecycleOwner, verifyScreenObserver)
        viewModel.notConnectionLiveData.observe(viewLifecycleOwner, notConnectionObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)

    }

    private fun loadViews() {
        binding.keyboard.apply {
            numberList.add(btZero)
            numberList.add(btOne)
            numberList.add(btTwo)
            numberList.add(btThree)
            numberList.add(btFour)
            numberList.add(btFive)
            numberList.add(btSix)
            numberList.add(btSeven)
            numberList.add(btEight)
            numberList.add(btNine)
        }
        for (index in 0 until 10) {
            numberList[index].setOnClickListener {
                if (code.length == 6) return@setOnClickListener
                code.append(index)
                binding.inputSmsCode.setText(code.toString())
            }
        }
    }

    private val notConnectionObserver = Observer<Unit> {
        showToast("Sizda mavjud emas")
    }
    private val progressObserver = Observer<Boolean> {
        if (it) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.INVISIBLE
    }
    private val errorObserver = Observer<String> { showToast(it) }
    private val verifyScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_verifyScreen_to_contactScreen)
    }
}
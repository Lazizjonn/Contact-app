package uz.gita.contactappretrofit.presentation.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactappretrofit.R
import uz.gita.contactappretrofit.databinding.DialogAddContactBinding
import uz.gita.contactappretrofit.utils.myApply


class AddContactDialog : DialogFragment(R.layout.dialog_add_contact) {
    private var addContactListener: ((String, String, String) -> Unit)? = null
    private val binding by viewBinding(DialogAddContactBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        close.setOnClickListener { dismiss() }
        save.setOnClickListener {
            addContactListener?.invoke(firstName.text.toString(), lastName.text.toString(), number.text.toString())
            dismiss()
        }
    }

    fun setAddContactListener(block: (String, String,String) -> Unit) {
        addContactListener = block
    }
}



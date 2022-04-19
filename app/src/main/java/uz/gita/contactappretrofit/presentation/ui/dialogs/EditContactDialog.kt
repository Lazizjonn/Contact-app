package uz.gita.contactappretrofit.presentation.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.EditText
import androidx.cardview.widget.CardView
import uz.gita.contactappretrofit.R


class EditContactDialog( context: Context, private val oldFirstName: String, private val oldLastName: String, private val oldPhoneNumber: String) : AlertDialog(context) {

    private var editContactListener: ((String, String, String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_contact, null, false)
        setContentView(view)

        window?.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)

        val inputFirstName: EditText = view.findViewById(R.id.firstName)
        val inputLastName: EditText = view.findViewById(R.id.lastName)
        val inputPhoneNumber: EditText = view.findViewById(R.id.number)
        val buttonClose: CardView = view.findViewById(R.id.close)
        val buttonSave: CardView = view.findViewById(R.id.save)

        inputFirstName.setText(oldFirstName)
        inputLastName.setText(oldLastName)
        inputPhoneNumber.setText(oldPhoneNumber)

        buttonClose.setOnClickListener { dismiss() }
        buttonSave.setOnClickListener {
            editContactListener?.invoke(inputFirstName.text.toString(), inputLastName.text.toString(), inputPhoneNumber.text.toString())
            dismiss()
        }
    }

    fun setEditContactListener(block: (String, String, String) -> Unit) {
        editContactListener = block
    }
}

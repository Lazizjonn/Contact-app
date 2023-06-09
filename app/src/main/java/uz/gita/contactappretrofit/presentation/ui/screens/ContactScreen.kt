package uz.gita.contactappretrofit.presentation.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.contactappretrofit.R
import uz.gita.contactappretrofit.data.model.firebase.ContactDataFireBase
import uz.gita.contactappretrofit.data.model.request.ContactRequest
import uz.gita.contactappretrofit.data.model.response.ContactResponse
import uz.gita.contactappretrofit.databinding.FragmentContactScreenBinding
import uz.gita.contactappretrofit.presentation.ui.adapter.ContactAdapter
import uz.gita.contactappretrofit.presentation.ui.adapter.ContactFirebaseAdapter
import uz.gita.contactappretrofit.presentation.ui.dialogs.AddContactDialog
import uz.gita.contactappretrofit.presentation.ui.dialogs.EditContactDialog
import uz.gita.contactappretrofit.presentation.ui.dialogs.EventDialog
import uz.gita.contactappretrofit.presentation.viewmodels.ContactViewModel
import uz.gita.contactappretrofit.presentation.viewmodels.impl.ContactViewModelImpl
import uz.gita.contactappretrofit.utils.myApply

@AndroidEntryPoint
class ContactScreen : Fragment(R.layout.fragment_contact_screen) {

    private val viewModel: ContactViewModel by viewModels<ContactViewModelImpl>()
    private val binding by viewBinding(FragmentContactScreenBinding::bind)
//    private val adapter by lazy(LazyThreadSafetyMode.NONE) { ContactAdapter() }
    private val adapter2 by lazy(LazyThreadSafetyMode.NONE) { ContactFirebaseAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {

        contactList.adapter = adapter2
        contactList.layoutManager = LinearLayoutManager(requireContext())
        buttonAdd.setOnClickListener { viewModel.showAddContactDialog() }
        adapter2.setSelectContactDataListener { viewModel.showEventDialog(it) }

/*        viewModel.contactLiveData.observe(viewLifecycleOwner, contactObserver)
        viewModel.insertLiveData.observe(viewLifecycleOwner, insertObserver)
        viewModel.showAddContactDialogLiveData.observe(viewLifecycleOwner, showAddContactDialogObserver)
        viewModel.showEventDialogLiveData.observe(viewLifecycleOwner, showEventDialogObserver)
        viewModel.showEditDialogLiveData.observe(viewLifecycleOwner, showEditDialogObserver)
        viewModel.errorResponseLiveData.observe(viewLifecycleOwner, errorResponseObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        viewModel.notConnectionLiveData.observe(viewLifecycleOwner, notConnectionObserver)
        viewModel.deletedItemLiveData.observe(viewLifecycleOwner, deletedItemObserver)
        viewModel.updateItemLiveData.observe(viewLifecycleOwner, updateItemObserver)*/
        viewModel.contactFirebaseLiveData.observe(viewLifecycleOwner, contactFirebaseObserver)
        viewModel.errorFireBaseLiveData.observe(viewLifecycleOwner, errorFirebaseObserver)
    }

/*    private val contactObserver = Observer<List<ContactResponse>> {
        adapter.submitList(it.toMutableList())
    }
    private val deletedItemObserver = Observer<Long> {
        viewModel.load()
    }
    private val showAddContactDialogObserver = Observer<Unit> {
        val dialog = AddContactDialog()
        dialog.setAddContactListener { firstName, lastName, phone ->
            viewModel.insertContact(ContactRequest(firstName, lastName, phone))
        }
        dialog.show(childFragmentManager, "Add")
    }
    private val showEventDialogObserver = Observer<ContactDataFireBase> { data ->
        val eventDialog = EventDialog()
        eventDialog.setClickEditButtonListener { viewModel.showEditDialog(data) }
        eventDialog.setClickDeleteButtonListener {
            Log.d("TAG", "delete data id = : " + data.id)
            viewModel.delete(data.id)
        }
        eventDialog.show(childFragmentManager, "Event")
    }
    private val showEditDialogObserver = Observer<ContactDataFireBase> { data ->
        val editDialog =
            EditContactDialog(requireContext(), data.firstName, data.lastName, data.phone)

        editDialog.setEditContactListener { firstName, lastNam, phone ->
            viewModel.update(ContactResponse(data.id, firstName, lastNam, phone))
        }
        editDialog.show()
    }
    private val progressObserver = Observer<Boolean> {
        if (it) binding.progress.show()
        else binding.progress.hide()
    }
    private val notConnectionObserver = Observer<Unit> {
        Toast.makeText(requireContext(), "Sizda internet mavjud emas", Toast.LENGTH_SHORT).show()
    }
    private val errorResponseObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
    private val insertObserver = Observer<Unit> {
        viewModel.load()
    }
    private val updateItemObserver = Observer<Unit> {
        viewModel.load()
    }*/

    private val contactFirebaseObserver = Observer<List<ContactDataFireBase>> {
        adapter2.submitList(it.toMutableList())
    }

    private val errorFirebaseObserver = Observer<String> {

    }
}


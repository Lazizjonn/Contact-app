package uz.gita.contactappretrofit.presentation.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.contactappretrofit.data.remote.data.request.ContactRequest
import uz.gita.contactappretrofit.data.remote.data.response.ContactResponse

interface ContactViewModel {

    val contactLiveData: LiveData<List<ContactResponse>>
    val insertLiveData: LiveData<Unit>
    val progressLiveData: LiveData<Boolean>
    val errorResponseLiveData: LiveData<String>
    val deletedItemLiveData: LiveData<Long>
    val notConnectionLiveData: LiveData<Unit>
    val showAddContactDialogLiveData: LiveData<Unit>
    val showEventDialogLiveData: LiveData<ContactResponse>
    val showEditDialogLiveData: LiveData<ContactResponse>
    val updateItemLiveData: LiveData<Unit>

    fun load()
    fun insertContact(data: ContactRequest)
    fun update(data: ContactResponse)
    fun delete(id: Long)

    fun showAddContactDialog()
    fun showEventDialog(data: ContactResponse)
    fun showEditDialog(data: ContactResponse)
}
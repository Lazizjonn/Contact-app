package uz.gita.contactappretrofit.presentation.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.contactappretrofit.data.model.firebase.ContactDataFireBase
import uz.gita.contactappretrofit.data.model.request.ContactRequest
import uz.gita.contactappretrofit.data.model.response.ContactResponse

interface ContactViewModel {

    val contactLiveData: LiveData<List<ContactResponse>>
    val insertLiveData: LiveData<Unit>
    val progressLiveData: LiveData<Boolean>
    val errorResponseLiveData: LiveData<String>
    val deletedItemLiveData: LiveData<Long>
    val notConnectionLiveData: LiveData<Unit>
    val showAddContactDialogLiveData: LiveData<Unit>
    val showEventDialogLiveData: LiveData<ContactDataFireBase>
    val showEditDialogLiveData: LiveData<ContactDataFireBase>
    val updateItemLiveData: LiveData<Unit>
    val contactFirebaseLiveData : LiveData<List<ContactDataFireBase>>
    val errorFireBaseLiveData: LiveData<String>


    fun load()
    fun insertContact(data: ContactRequest)
    fun update(data: ContactResponse)
    fun delete(id: Long)

    fun showAddContactDialog()
    fun showEventDialog(data: ContactDataFireBase)
    fun showEditDialog(data: ContactDataFireBase)
}
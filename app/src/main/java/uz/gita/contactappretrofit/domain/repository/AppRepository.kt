package uz.gita.contactappretrofit.domain.repository

import uz.gita.contactappretrofit.data.model.firebase.ContactDataFireBase

interface AppRepository {
    fun getAllContactFromFireBase(
        dataBlock: (List<ContactDataFireBase>) -> Unit,
        errorBlock: (String) -> Unit
    )
}
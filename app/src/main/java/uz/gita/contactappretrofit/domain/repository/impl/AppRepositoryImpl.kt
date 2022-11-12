package uz.gita.contactappretrofit.domain.repository.impl

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import uz.gita.contactappretrofit.data.model.firebase.ContactDataFireBase
import uz.gita.contactappretrofit.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val ref: DatabaseReference
) : AppRepository {
    override fun getAllContactFromFireBase(dataBlock: (List<ContactDataFireBase>) -> Unit, errorBlock: (String) -> Unit) {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = ArrayList<ContactDataFireBase>()

                snapshot.children.forEach {
                    it.getValue(ContactDataFireBase::class.java)?.let { it2 -> data.add(it2) }
                }
                dataBlock.invoke(data)

            }

            override fun onCancelled(snapshot: DatabaseError) {

            }

        })
    }

}
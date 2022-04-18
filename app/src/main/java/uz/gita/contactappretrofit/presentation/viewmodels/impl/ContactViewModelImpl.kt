package uz.gita.contactappretrofit.presentation.viewmodels.impl

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.contactappretrofit.data.local.MySharedPref
import uz.gita.contactappretrofit.data.remote.api.ContactApi
import uz.gita.contactappretrofit.data.remote.data.request.ContactRequest
import uz.gita.contactappretrofit.data.remote.data.response.ContactResponse
import uz.gita.contactappretrofit.data.remote.data.response.DeleteContactResponse
import uz.gita.contactappretrofit.presentation.viewmodels.ContactViewModel
import uz.gita.contactappretrofit.utils.isConnected
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class ContactViewModelImpl@Inject constructor(private val api: ContactApi, private val pref : MySharedPref) : ViewModel() , ContactViewModel{

    override val contactLiveData = MutableLiveData<List<ContactResponse>>()
    override val insertLiveData = MutableLiveData<Unit>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorResponseLiveData = MutableLiveData<String>()
    override val deletedItemLiveData = MutableLiveData<Long>()
    override val notConnectionLiveData = MutableLiveData<Unit>()
    override val showAddContactDialogLiveData = MutableLiveData<Unit>()
    override val showEventDialogLiveData = MutableLiveData<ContactResponse>()
    override val showEditDialogLiveData = MutableLiveData<ContactResponse>()
    override val updateItemLiveData = MutableLiveData<Unit>()

    private val executor = Executors.newSingleThreadExecutor()
    private val handler by lazy { Handler(Looper.getMainLooper()) }
    private val token = pref.token

    init {
        load()
    }

    override fun load() {
        if (!isConnected()) {
            // room  -> contactLiveData.postValue(it)
        } else {
            executor.execute {
                try {
                    val response = api.getAllContact(token).execute()
                    progressLiveData.postValue(false)
                    if (response.isSuccessful) {
                        response.body()?.let {
                            contactLiveData.postValue(it)
                        }
                    } else {
                        errorResponseLiveData.postValue("Unsuccessful")
                    }
                } catch (t: Throwable) {
                    handler.post {
                        progressLiveData.value = false
                        errorResponseLiveData.value = (t.message)
                    }
                }
            }


/*            progressLiveData.value = true
            api.getAllContact().enqueue(object : Callback<List<ContactResponse>> {
                override fun onResponse(
                    call: Call<List<ContactResponse>>,
                    response: Response<List<ContactResponse>>
                ) {
                    progressLiveData.postValue(false)
                    if (response.isSuccessful) {
                        response.body()?.let {
                            contactLiveData.value = it
                            list.clear()
                            list.addAll(it)
                        }
                    } else {
                        errorResponseLiveData.value = ("Unsuccessful")
                    }
                }

                override fun onFailure(call: Call<List<ContactResponse>>, t: Throwable) {
                    progressLiveData.value = (false)
                    errorResponseLiveData.value = (t.message)
                }
            })*/
        }
    }
    override fun insertContact(data: ContactRequest) {

        progressLiveData.value = true
        api.addContact(token,data).enqueue(object : Callback<ContactResponse> {
            override fun onResponse(call: Call<ContactResponse>, response: Response<ContactResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        insertLiveData.value = Unit
                        progressLiveData.value=(false)
                    }
                } else {
                    progressLiveData.postValue(false)
                    errorResponseLiveData.postValue("Unsuccessful")
                }
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                errorResponseLiveData.postValue(t.message)
                progressLiveData.postValue(false)

            }
        })
    }
    override fun update(data: ContactResponse) {

        progressLiveData.value = true
        api.updateContact(token, data).enqueue(object : Callback<ContactResponse> {
            override fun onResponse(call: Call<ContactResponse>, response: Response<ContactResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        updateItemLiveData.value = Unit
                        progressLiveData.postValue(false)
                    }
                } else {
                    errorResponseLiveData.postValue("Unsuccessful")
                    progressLiveData.postValue(false)
                }
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                errorResponseLiveData.postValue(t.message)
                progressLiveData.postValue(false)
            }

        })

    }
    override fun delete(id: Long) {
        Log.d("TAG", "delete2 data id = : "+ id)

        progressLiveData.value = true
        api.deleteContact(token, id).enqueue(object : Callback<DeleteContactResponse> {
            override fun onResponse(
                call: Call<DeleteContactResponse>, response: Response<DeleteContactResponse>
            ) {
                Log.d("TAG", "delete3 data id = : "+ response.body()?.id)
                progressLiveData.postValue(false)
                if (response.isSuccessful) {

                    deletedItemLiveData.postValue(response.body()?.id)
                } else {
                    progressLiveData.postValue(false)
                    errorResponseLiveData.postValue("Unsuccessful")
                }
            }

            override fun onFailure(call: Call<DeleteContactResponse>, t: Throwable) {
                Log.d("TAG", "delete4 data id message = : "+t.message)
                progressLiveData.postValue(false)
                errorResponseLiveData.postValue(t.message)
            }
        })



    }
    override fun showAddContactDialog() {
        showAddContactDialogLiveData.value = Unit
    }
    override fun showEventDialog(data: ContactResponse) {
        showEventDialogLiveData.value = data
    }
    override fun showEditDialog(data: ContactResponse) {
        showEditDialogLiveData.value = data
    }
}
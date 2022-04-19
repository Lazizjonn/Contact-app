package uz.gita.contactappretrofit.data.source.remote.api

import retrofit2.Call
import retrofit2.http.*
import uz.gita.contactappretrofit.data.model.request.ContactRequest
import uz.gita.contactappretrofit.data.model.request.Login2Request
import uz.gita.contactappretrofit.data.model.request.Register2Request
import uz.gita.contactappretrofit.data.model.request.Verify2Request
import uz.gita.contactappretrofit.data.model.response.*

interface ContactApi {

    @POST("/api/v1/register")
    fun registerUser(@Body data: Register2Request): Call<Register2Response>

    @POST("/api/v1/register/verify")
    fun verifyUser(@Body data: Verify2Request): Call<Verify2Response>

    @POST("/api/v1/login")
    fun loginUser(@Body data: Login2Request): Call<Login2Response>

    @POST("/api/v1/contact")
    fun addContact(@Header("token") token : String, @Body data: ContactRequest): Call<ContactResponse>

    @PUT("/api/v1/contact")
    fun updateContact(@Header("token") token: String, @Body data: ContactResponse): Call<ContactResponse>

    @DELETE("/api/v1/contact")
    fun deleteContact(@Header("token") token: String, @Query("id") id: Long): Call<DeleteContactResponse>

    @GET("/api/v1/contact")
    fun getAllContact(@Header("token") token: String): Call<List<ContactResponse>>
}
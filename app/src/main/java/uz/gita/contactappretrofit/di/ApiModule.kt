package uz.gita.contactappretrofit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.contactappretrofit.data.remote.api.ContactApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @[Provides Singleton]
    fun logging(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    @[Provides Singleton]
    fun getRetrofitObject(): Retrofit = Retrofit.Builder().baseUrl("http://ef15-87-18-142-226.ngrok.io")
        .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    @[Provides Singleton]
    fun getContactApi(retrofit: Retrofit): ContactApi = retrofit.create(ContactApi::class.java)
    var client = OkHttpClient.Builder().addInterceptor(logging()).build()
}
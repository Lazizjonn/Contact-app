package uz.gita.contactappretrofit.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class FirebaseModule {

    @[Provides Singleton]
    fun getRealTimeDataBase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @[Provides Singleton]
    fun getContactReference(database: FirebaseDatabase): DatabaseReference = database.getReference("contacts")

}
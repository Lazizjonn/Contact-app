package uz.gita.contactappretrofit.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.contactappretrofit.data.source.local.room.database.MyRoomDatabase

@[Module InstallIn(SingletonComponent::class)]
class DatabaseModule {

    @Provides
    fun getDatabase(@ApplicationContext context: Context): MyRoomDatabase = Room.databaseBuilder(context, MyRoomDatabase::class.java, "MyRoomDatabase")
        .allowMainThreadQueries()
        .build()
}
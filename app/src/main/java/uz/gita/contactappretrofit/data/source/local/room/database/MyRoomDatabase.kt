package uz.gita.contactappretrofit.data.source.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.contactappretrofit.data.source.local.room.dao.ContactDao
import uz.gita.contactappretrofit.data.source.local.room.entity.ContactData


@Database(entities = [ContactData::class], version = 1)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun getContactDao(): ContactDao

    companion object {

        private lateinit var instance: MyRoomDatabase

        fun init(context: Context) {
            if (::instance.isInitialized) return
            instance = Room.databaseBuilder(context, MyRoomDatabase::class.java, "MyRoomDatabase")
                .allowMainThreadQueries()
                .build()
        }

        fun getDataBase(): MyRoomDatabase {
            return instance
        }

    }


}
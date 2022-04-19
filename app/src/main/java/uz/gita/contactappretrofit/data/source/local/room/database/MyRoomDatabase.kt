package uz.gita.contactappretrofit.data.source.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.contactappretrofit.data.source.local.room.dao.ContactDao
import uz.gita.contactappretrofit.data.source.local.room.entity.ContactData


@Database(entities = [ContactData::class], version = 1)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun getContactDao(): ContactDao

}
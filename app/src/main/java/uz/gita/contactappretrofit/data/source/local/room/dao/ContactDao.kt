package uz.gita.contactappretrofit.data.source.local.room.dao

import androidx.room.*
import uz.gita.contactappretrofit.data.source.local.room.entity.ContactData


@Dao
interface ContactDao {

    @Query("Select * from  ContactData")
    fun getAllContact(): List<ContactData>

    @Insert
    fun insert(data: ContactData)

    @Delete
    fun delete(data: ContactData)

    @Update
    fun update(data: ContactData)
}


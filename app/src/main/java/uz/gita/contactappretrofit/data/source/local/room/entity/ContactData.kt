package uz.gita.contactappretrofit.data.source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ContactData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userName: String,
    val phoneNumber: String
)
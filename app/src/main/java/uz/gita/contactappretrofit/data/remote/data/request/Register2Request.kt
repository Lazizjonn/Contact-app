package uz.gita.contactappretrofit.data.remote.data.request

import android.os.Parcel
import android.os.Parcelable

data class Register2Request(
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val password: String?
    ) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(phone)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Register2Request> {
        override fun createFromParcel(parcel: Parcel): Register2Request {
            return Register2Request(parcel)
        }

        override fun newArray(size: Int): Array<Register2Request?> {
            return arrayOfNulls(size)
        }
    }
}

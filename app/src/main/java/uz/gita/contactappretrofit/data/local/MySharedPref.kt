package uz.gita.contactappretrofit.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MySharedPref @Inject constructor(@ApplicationContext context: Context) {

    private val pref = context.getSharedPreferences("Contact", Context.MODE_PRIVATE)

    var token: String
        get() = pref.getString("TOKEN", "")!!
        set(value) = pref.edit().putString("TOKEN", value).apply()
}
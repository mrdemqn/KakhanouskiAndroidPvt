package by.itacademy.pvt.utils

import android.content.Context
import android.content.SharedPreferences

private const val SHARED_PREFERENCES_NAME = "vvww"
private const val TEXT_KEY = "wwvv"

class AppPrefManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context
        .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun saveUserText(text: String) {
        sharedPreferences
            .edit()
            .putString(TEXT_KEY, text)
            .apply()
    }

    fun getUserText(): String {
        return sharedPreferences.getString(TEXT_KEY, "")
            ?: "This is null"
    }
}
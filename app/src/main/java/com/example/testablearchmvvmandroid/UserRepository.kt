package com.example.testablearchmvvmandroid

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.delay

class UserRepository(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    suspend fun getUserName(): String {
        delay(2000)
        return sharedPreferences.getString(PREF_NAME_KEY, PREF_NAME_DEFAULT) ?: PREF_NAME_DEFAULT
    }

    fun saveUserName(name: String) {
        sharedPreferences.edit().putString(PREF_NAME_KEY, name).apply()
    }

    companion object {
        private const val PREF_NAME_KEY = "user_name"
        private const val PREF_NAME_DEFAULT = "Usuário Padrão"
    }
}
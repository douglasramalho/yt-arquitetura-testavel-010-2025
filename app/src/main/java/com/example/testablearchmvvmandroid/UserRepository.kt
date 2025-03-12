package com.example.testablearchmvvmandroid

interface UserRepository {

    suspend fun getUserName(): String

    fun saveUserName(name: String)
}
package com.example.testablearchmvvmandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userName = MutableStateFlow("Carregando...")
    val userName = _userName.asStateFlow()

    init {
        loadUserName()
    }

    private fun loadUserName() {
        viewModelScope.launch {
            _userName.value = repository.getUserName()
        }
    }

    fun saveUserName(name: String) {
        repository.saveUserName(name)
        _userName.value = name
    }
}
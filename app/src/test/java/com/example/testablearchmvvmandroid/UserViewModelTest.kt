package com.example.testablearchmvvmandroid

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class UserViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockUserRepository = object : UserRepository {
        private var userName = "Usuário Padrão"

        override suspend fun getUserName(): String {
            return userName
        }

        override fun saveUserName(name: String) {
            userName = name
        }

    }

    private lateinit var viewModel: UserViewModel

    @Test
    fun `loadUserName should update userName`() = runTest {
        viewModel = UserViewModel(mockUserRepository)
        assertEquals("Usuário Padrão", viewModel.userName.value)
    }
}
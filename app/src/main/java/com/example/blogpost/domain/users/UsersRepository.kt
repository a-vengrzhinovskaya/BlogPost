package com.example.blogpost.domain.users

import com.example.blogpost.domain.users.models.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUsers(): Flow<List<User>>

    fun getUserById(id: String): Flow<User>

    fun createUser(
        email: String,
        password: String,
        name: String,
        avatarUrl: String
    ): Flow<User>

    suspend fun login(email: String, password: String)

    suspend fun register(email: String, password: String, name: String, avatarUrl: String)

    suspend fun updateProfile(name: String, email: String, avatarUrl: String)

    suspend fun logOut()

    suspend fun deleteUser()

    suspend fun getCurrentUser(): User?

    suspend fun isAuthorized(): Boolean
}
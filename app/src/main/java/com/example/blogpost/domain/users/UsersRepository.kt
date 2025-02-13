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

    fun login(email: String, password: String): Flow<Unit>

    fun register(email: String, password: String): Flow<User>

    fun logOut(): Flow<Unit>

    fun getCurrentUser(): Flow<User?>
}
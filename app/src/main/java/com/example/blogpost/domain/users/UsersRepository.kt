package com.example.blogpost.domain.users

import com.example.blogpost.domain.users.models.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUsers(): Flow<List<User>>

    fun getUserById(id: String): Flow<User>
}
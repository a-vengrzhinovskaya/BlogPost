package com.example.blogpost.data.repositories.users

import com.example.blogpost.data.network.BlogPostAPI
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.domain.users.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class UsersRepositoryImpl(
    private val api: BlogPostAPI,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) : UsersRepository {
    private val usersCache = mutableListOf<User>()

    override fun getUsers(): Flow<List<User>> = flow {
        val users = api.getAllUsers().records.map {
            it.toDomain()
        }
        cacheUsers(users)
        emit(users)
    }.flowOn(coroutineContext)

    override fun getUserById(id: String): Flow<User> = flow {
        val user = usersCache.firstOrNull { it.id == id } ?: api.getUserById(id).toDomain()
        emit(user)
    }.flowOn(coroutineContext)

    private fun cacheUsers(newUsers: List<User>) = newUsers.forEach { newUser ->
        if (!usersCache.contains(newUser)) {
            usersCache.add(newUser)
        }
    }
}
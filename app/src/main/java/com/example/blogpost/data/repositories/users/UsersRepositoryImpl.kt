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
    override fun getUsers(): Flow<List<User>> = flow {
        val users = api.getAllUsers().records.map {
            it.toDomain()
        }
        emit(users)
    }.flowOn(coroutineContext)

    override fun getUserById(id: String): Flow<User> = flow {
        val user = api.getUserById(id).toDomain()
        emit(user)
    }.flowOn(coroutineContext)
}
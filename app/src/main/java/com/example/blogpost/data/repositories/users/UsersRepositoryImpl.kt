package com.example.blogpost.data.repositories.users

import android.content.SharedPreferences
import com.example.blogpost.data.network.BlogPostAPI
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.domain.users.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlin.coroutines.CoroutineContext

private const val BLOGPOST_USER_ID = "blog_post_user_id"

class UsersRepositoryImpl(
    private val api: BlogPostAPI,
    private val sharedPreferences: SharedPreferences,
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

    override fun login(email: String, password: String): Flow<Unit> = flow<Unit> {
        sharedPreferences.edit().putString(BLOGPOST_USER_ID, email).apply()
    }.flowOn(coroutineContext)

    override fun register(email: String, password: String): Flow<User> {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): Flow<User?> = flow {
        getCurrentUserId().last().let {
            val user = if (it != null) getUserById(it).last() else null
            emit(user)
        }
    }.flowOn(coroutineContext)

    private fun getCurrentUserId(): Flow<String?> = flow {
        val currentUserId = sharedPreferences.getString(BLOGPOST_USER_ID, null)
        emit(currentUserId)
    }.flowOn(coroutineContext)

    private fun cacheUsers(newUsers: List<User>) = newUsers.forEach { newUser ->
        if (!usersCache.contains(newUser)) {
            usersCache.add(newUser)
        }
    }
}
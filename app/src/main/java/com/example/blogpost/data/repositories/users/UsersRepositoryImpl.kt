package com.example.blogpost.data.repositories.users

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.blogpost.data.network.BlogPostAPI
import com.example.blogpost.data.network.models.users.UsersResponse
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.domain.users.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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
        val users = api.getAllUsers().records.map { it.toDomain() }
        cacheUsers(users)
        emit(users)
    }.flowOn(coroutineContext)

    override fun getUserById(id: String): Flow<User> = flow {
        val user = usersCache.firstOrNull { it.id == id } ?: api.getUserById(id).toDomain()
        emit(user)
    }.flowOn(coroutineContext)

    override fun createUser(
        email: String,
        password: String,
        name: String,
        avatarUrl: String
    ) = flow {
        val user = api.createUser(
            UsersResponse(
                records = listOf(
                    UsersResponse.Record(
                        user = UsersResponse.Record.User(
                            email = email,
                            password = password,
                            name = name,
                            avatarUrl = avatarUrl
                        )
                    )
                )
            )
        )
        emit(user.records.first().toDomain())
    }.flowOn(coroutineContext)

    override suspend fun login(email: String, password: String) =
        checkIfUserExists(email, password)
            ?.let { sharedPreferences.edit { putString(BLOGPOST_USER_ID, it.id) } }
            ?: error("User not found")

    override suspend fun register(
        email: String,
        password: String,
        name: String,
        avatarUrl: String
    ) {
        checkIfUserExists(email, password)?.let { error("User already exists") }
        createUser(email, password, name, avatarUrl)
    }

    override suspend fun logOut() = sharedPreferences.edit { remove(BLOGPOST_USER_ID) }

    override fun getCurrentUser(): Flow<User?> = flow {
        getCurrentUserId().last().let {
            val user = if (it != null) getUserById(it).last() else null
            emit(user)
        }
    }.flowOn(coroutineContext)

    private suspend fun checkIfUserExists(email: String, password: String) = getUsers().first()
        .firstOrNull { it.email.lowercase() == email.lowercase() && it.password == password }

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
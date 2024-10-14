// UserRepository.kt
package com.example.myandroidplayground.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class UserRepository @Inject constructor() {
    private val cache = MutableStateFlow<UserData?>(null)
    private val countries = listOf("UK", "DE", "US", "FR", "IT")

    suspend fun fetchUserData() {
        // Simulate network delay
        delay(2000)

        // Simulate fetching user data from a repository or network
        val userData = UserData(
            merchantCode = "123456",
            country = countries.random(),
            isPremium = Random.nextBoolean()
        )

        // Update the cache
        cache.emit(userData)
    }

    fun observeUserData(): Flow<UserData?> = cache
}

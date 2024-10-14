// UserRepository.kt
package com.example.myandroidplayground

import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class UserRepository @Inject constructor() {
    private val countries = listOf("UK", "DE", "US", "FR", "IT")

    suspend fun getUserData(): UserData {
        // Simulate network delay
        delay(2000)

        // Simulate fetching user data from a repository or network
        return UserData(
            merchantCode = "123456",
            country = countries.random(),
            isPremium = Random.nextBoolean()
        )
    }
}

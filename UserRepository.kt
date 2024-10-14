// UserRepository.kt
package com.example.myandroidplayground

import javax.inject.Inject
import kotlin.random.Random

class UserRepository @Inject constructor() {
    private val countries = listOf("UK", "DE", "US", "FR", "IT")

    fun getUserData(): UserData {
        // Simulate fetching user data from a repository or network
        return UserData(
            merchantCode = "123456",
            country = countries.random(),
            isPremium = Random.nextBoolean()
        )
    }
}

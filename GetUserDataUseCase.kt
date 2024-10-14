// GetUserDataUseCase.kt
package com.example.myandroidplayground

import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(): UserData {
        return userRepository.getUserData()
    }
}

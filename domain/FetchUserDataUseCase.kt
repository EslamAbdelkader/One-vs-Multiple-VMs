// FetchUserDataUseCase.kt
package com.example.myandroidplayground.domain

import com.example.myandroidplayground.data.UserRepository
import javax.inject.Inject

class FetchUserDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        userRepository.fetchUserData()
    }
}

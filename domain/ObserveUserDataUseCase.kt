// ObserveUserDataUseCase.kt
package com.example.myandroidplayground.domain

import com.example.myandroidplayground.data.UserData
import com.example.myandroidplayground.data.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveUserDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<UserData?> {
        return userRepository.observeUserData()
    }
}

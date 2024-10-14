// FetchBusinessAccountDataUseCase.kt
package com.example.myandroidplayground

import javax.inject.Inject

class FetchAccountOverviewData @Inject constructor(
    private val repository: BusinessAccountRepository,
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke() {
        println("UserRepository from FetchAccountOverviewData: $userRepository")
        userRepository.fetchUserData()
        repository.fetchBusinessAccountData()
    }
}

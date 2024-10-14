// FetchBusinessAccountDataUseCase.kt
package com.example.myandroidplayground.domain

import com.example.myandroidplayground.data.BusinessAccountRepository
import javax.inject.Inject

class FetchBusinessAccountDataUseCase @Inject constructor(
    private val repository: BusinessAccountRepository
) {
    suspend operator fun invoke() {
        repository.fetchBusinessAccountData()
    }
}

// FetchAccountOverviewData.kt
package com.example.myandroidplayground.domain

import javax.inject.Inject

class FetchAccountOverviewData @Inject constructor(
    private val fetchUserDataUseCase: FetchUserDataUseCase,
    private val fetchBusinessAccountDataUseCase: FetchBusinessAccountDataUseCase
) {
    suspend operator fun invoke() {
        fetchUserDataUseCase()
        fetchBusinessAccountDataUseCase()
    }
}

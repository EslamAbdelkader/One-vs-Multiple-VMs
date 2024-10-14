// ObserveBusinessAccountDataUseCase.kt
package com.example.myandroidplayground.domain

import com.example.myandroidplayground.data.BusinessAccountRepository
import com.example.myandroidplayground.data.BusinessAccountData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveBusinessAccountDataUseCase @Inject constructor(
    private val repository: BusinessAccountRepository
) {
    operator fun invoke(): Flow<BusinessAccountData?> {
        return repository.observeBusinessAccountData()
    }
}

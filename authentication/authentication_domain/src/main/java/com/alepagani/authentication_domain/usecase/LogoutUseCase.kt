package com.alepagani.authentication_domain.usecase

import com.alepagani.authentication_domain.repository.AuthenticationRepository

class LogoutUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke() {
        return repository.logout()
    }
}

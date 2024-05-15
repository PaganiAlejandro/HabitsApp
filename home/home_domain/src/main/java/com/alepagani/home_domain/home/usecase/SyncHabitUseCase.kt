package com.alepagani.home_domain.home.usecase

import com.alepagani.home_domain.repository.HomeRepository

class SyncHabitUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke() {
        repository.syncHabits()
    }
}
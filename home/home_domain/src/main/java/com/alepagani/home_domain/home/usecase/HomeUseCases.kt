package com.alepagani.home_domain.home.usecase

data class HomeUseCases(
    val completeHabitUseCase: CompleteHabitUseCase,
    val getHabitsForDateUseCase: GetHabitsForDateUseCase,
    val syncHabitUseCase: SyncHabitUseCase
)

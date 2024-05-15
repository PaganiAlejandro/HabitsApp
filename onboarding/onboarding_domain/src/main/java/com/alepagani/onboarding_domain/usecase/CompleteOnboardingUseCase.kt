package com.alepagani.onboarding_domain.usecase

import com.alepagani.onboarding_domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(
    private val repository: OnboardingRepository
) {
    operator fun invoke() {
        repository.completeOnboarding()
    }
}

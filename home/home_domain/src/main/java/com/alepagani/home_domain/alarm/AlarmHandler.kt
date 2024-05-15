package com.alepagani.home_domain.alarm

import com.alepagani.home_domain.models.Habit

interface AlarmHandler {
    fun setRecurringAlarm(habit: Habit)
    fun cancel(habit: Habit)
}
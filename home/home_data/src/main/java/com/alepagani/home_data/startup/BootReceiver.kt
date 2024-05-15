package com.alepagani.home_data.startup

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.alepagani.home_data.extension.goAsync
import com.alepagani.home_data.local.HomeDao
import com.alepagani.home_data.mapper.toDomain
import com.alepagani.home_domain.alarm.AlarmHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {
    @Inject
    lateinit var alarmHandler: AlarmHandler

    @Inject
    lateinit var homeDao: HomeDao

    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        if (context == null || intent == null) return@goAsync
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return@goAsync

        val items = homeDao.getAllHabits()
        items.forEach {
            alarmHandler.setRecurringAlarm(it.toDomain())
        }
    }
}

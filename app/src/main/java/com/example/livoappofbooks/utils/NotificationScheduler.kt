package com.example.livoappofbooks.utils

import android.content.Context
import androidx.work.*
import com.example.livoappofbooks.worker.ReadingReminderWorker
import java.util.Calendar
import java.util.concurrent.TimeUnit

object NotificationScheduler {

        fun schedulePeriodicReminder(context: Context) {
            val workManager = WorkManager.getInstance(context)

            val periodicRequest = PeriodicWorkRequestBuilder<ReadingReminderWorker>(
                3, TimeUnit.HOURS
            )
                .setInitialDelay(
                    1,
                    TimeUnit.SECONDS
                )
                .addTag("reading_reminder")
                .build()

            workManager.enqueueUniquePeriodicWork(
                "PeriodicReadingReminder",
                ExistingPeriodicWorkPolicy.UPDATE,
                periodicRequest
            )
        }

        fun cancelReminder(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork("PeriodicReadingReminder")
        }
}
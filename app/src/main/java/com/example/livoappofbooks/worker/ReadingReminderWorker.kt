package com.example.livoappofbooks.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.livoappofbooks.utils.NotificationService
import com.example.livoappofbooks.utils.ReadingMessages

class ReadingReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val randomContent = ReadingMessages.list.random()

        val title = randomContent.title
        val message = randomContent.message

        val notificationService = NotificationService(applicationContext)
        notificationService.showReadingReminder(title, message)

        return Result.success()
    }
}
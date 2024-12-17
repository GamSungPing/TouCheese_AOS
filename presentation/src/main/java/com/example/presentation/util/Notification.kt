package com.example.presentation.util

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.presentation.R
import com.example.presentation.main.view.MainActivity

object NotificationHelper {
    fun sendNotification(context: Context, title: String, messageBody: String) {
        val channelId = "fcm_default_channel"
        val notificationId = System.currentTimeMillis().toInt()

        val appContext = context.applicationContext

        val intent = Intent(appContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            appContext, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(appContext, channelId)
            .setSmallIcon(R.drawable.icon_favorite_24px)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .setContentIntent(pendingIntent)  // 알림 클릭 시 지정된 Intent 실행

        val notificationManager =
            appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 채널 설정
        val channel = NotificationChannel(
            channelId,
            "Default Channel",
            NotificationManager.IMPORTANCE_HIGH // 채널 중요도
        )

        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    fun showPermissionDeniedDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("알림 권한 필요")
            .setMessage("예약확인 알람을 받으려면 알림 권한을 허용해야 합니다. 설정에서 권한을 허용해 주세요.")
            .setPositiveButton("설정으로 이동") { _, _ ->
                val intent = Intent().apply {
                    action = android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS
                    putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, context.packageName)
                }
                context.startActivity(intent)
            }
            .setNegativeButton("취소", null)
            .show()
    }
}
package com.example.presentation.main.view.fragment

import android.content.ContentValues
import android.util.Log
import com.example.presentation.main.view.MainActivity.NotificationHelper.sendNotification
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener{ task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
            }

            val token = task.result
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    // 메시지 수신 시 호출
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let {
            val title = it.title ?: "Default Title"
            val message = it.body ?: "Default Body"
            sendNotification(applicationContext, title, message)
        }
    }
}
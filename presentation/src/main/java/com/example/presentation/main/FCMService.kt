package com.example.presentation.main

import com.example.presentation.main.view.NotificationHelper.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService(){
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
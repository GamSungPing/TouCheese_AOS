package com.example.data.service

import android.content.ContentValues
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

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
}
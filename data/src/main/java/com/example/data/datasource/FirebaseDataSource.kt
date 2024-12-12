package com.example.data.datasource

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class FirebaseDataSource @Inject constructor() {

    suspend fun getTokenFromFirebase(): String {
        return suspendCancellableCoroutine { continuation ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    continuation.resume(token)
                } else {
                    continuation.resumeWithException(task.exception ?: Exception("getTokenFromFirebase error"))
                }
            }
        }
    }
}
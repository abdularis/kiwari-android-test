package com.abdularis.chatapp.chatroom

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.abdularis.chatapp.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ChatRoomViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "ChatRoomViewModel"
        private const val ROOM_NAME = "room1"
    }

    val userMe: UserData
    val receiverUser: UserData
    private val sessionManager: SessionManager = SessionManager(application)
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        receiverUser = if (sessionManager.email == USER_JARJIT.email) USER_MAIL else USER_JARJIT
        userMe = UserData(
            name = sessionManager.name,
            avatar = sessionManager.avatar,
            email = sessionManager.email,
            password = ""
        )
    }

    val onMessagesLoaded = MutableLiveData<List<Message>>()

    fun logout() {
        sessionManager.isLogin = false
    }

    fun sendMessage(text: String) {
        val msg = MessageData(
            text = text,
            timestamp = System.currentTimeMillis(),
            sender = sessionManager.email
        )
        db.collection("chatrooms")
            .document(ROOM_NAME)
            .collection("messages")
            .document()
            .set(msg)
            .addOnSuccessListener {
                Log.d(TAG, "send message success")
            }
            .addOnFailureListener {
                Log.d(TAG, "send message failed")
            }
    }

    fun loadMessageData() {
        db.collection("chatrooms")
            .document(ROOM_NAME)
            .collection("messages")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                val messages = querySnapshot?.documents?.map {
                    val data = it.toObject(MessageData::class.java)
                    if (data == null) {
                        null
                    }
                    else {
                        val isSent = data.sender == sessionManager.email
                        Message(
                            text = data.text,
                            timestamp = data.timestamp,
                            senderEmail = data.sender,
                            isSent = isSent,
                            user = if (isSent) userMe else receiverUser
                        )
                    }
                }?.filterNotNull()

                messages?.let {
                    onMessagesLoaded.postValue(it)
                }
            }
    }
}
package com.abdularis.chatapp.chatroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdularis.chatapp.Message
import com.abdularis.chatapp.R
import com.abdularis.chatapp.formatTimestamp
import com.bumptech.glide.Glide
import java.util.ArrayList

class MessageAdapter: RecyclerView.Adapter<MessageViewHolder>() {

    companion object {
        private const val TYPE_TEXT_RECEIVED = 1
        private const val TYPE_TEXT_SENT = 2
    }

    private val messages = ArrayList<Message>()

    fun refreshItems(messages: List<Message>) {
        this.messages.clear()
        this.messages.addAll(messages)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isSent) {
            TYPE_TEXT_SENT
        } else {
            TYPE_TEXT_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if (viewType == TYPE_TEXT_SENT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_sent, parent, false)
            MessageSentViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_received, parent, false)
            MessageReceivedViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }
}

abstract class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    protected var message: TextView = itemView.findViewById(R.id.message)
    protected var avatar: ImageView = itemView.findViewById(R.id.avatar)
    protected var textDate: TextView = itemView.findViewById(R.id.textDate)
    abstract fun bind(msg: Message)
}

class MessageReceivedViewHolder(itemView: View): MessageViewHolder(itemView) {
    override fun bind(msg: Message) {
        message.text = msg.text
        textDate.text = formatTimestamp(msg.timestamp)
        Glide.with(itemView)
            .load("https://api.adorable.io/avatars/160/jarjit@mail.com.png")
            .into(avatar)
    }
}

class MessageSentViewHolder(itemView: View): MessageViewHolder(itemView) {
    override fun bind(msg: Message) {
        message.text = msg.text
        textDate.text = formatTimestamp(msg.timestamp)
        Glide.with(itemView)
            .load("https://api.adorable.io/avatars/160/jarjit@mail.com.png")
            .into(avatar)
    }
}
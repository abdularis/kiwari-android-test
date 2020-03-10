package com.abdularis.chatapp.chatroom

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdularis.chatapp.LoginActivity
import com.abdularis.chatapp.Message
import com.abdularis.chatapp.R
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : AppCompatActivity() {

    private lateinit var viewModel: ChatRoomViewModel
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        adapter = MessageAdapter()

        viewModel = ViewModelProviders.of(this).get(ChatRoomViewModel::class.java)
        viewModel.onMessagesLoaded.observe(this, Observer<List<Message>> {
            adapter.refreshItems(it)
        })
        viewModel.loadMessageData()

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        recyclerView.adapter = adapter
        btnSend.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val msg = etMessage.text.toString()
        if (!TextUtils.isEmpty(msg)) {
            etMessage.text.clear()
            viewModel.sendMessage(msg)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat_room_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionLogout) {
            viewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

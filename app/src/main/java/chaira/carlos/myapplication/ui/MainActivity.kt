package chaira.carlos.myapplication.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import chaira.carlos.myapplication.R
import chaira.carlos.myapplication.data.Message
import chaira.carlos.myapplication.utils.Constans.OPEN_GOOGLE
import chaira.carlos.myapplication.utils.Constans.OPEN_SEARCH
import chaira.carlos.myapplication.utils.Constans.RECEIVE_ID
import chaira.carlos.myapplication.utils.Constans.SEND_ID
import chaira.carlos.myapplication.utils.Time
import kotlinx.coroutines.*
import chaira.carlos.myapplication.utils.BotResponse

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    var messagesList = mutableListOf<Message>()

    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Peter", "Francesca", "Luigi", "Igor")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView()
        clickEvents()

        val random = (0..3).random()
        customBotMessage("Hello! Today you´re speaking with ${botList[random]}")
    }

    private fun clickEvents(){
        btn_send.setOnClickListener{
            sendMessage()
        }
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main){
                    rv_messages.scrollToPosition(adapter.itemCount-1)

                }
            }
        }

    }

    private fun recyclerView(){
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch{
            delay(100)
            withContext(Dispatchers.Main){
                rv_messages.scrollToPosition(adapter.itemCount-1)
            }
        }
    }

    private fun sendMessage(){
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if(message.isNotEmpty()){
            messagesList.add(Message(message, SEND_ID, timeStamp))
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount-1)
            botResponse(message)
        }
    }

    private fun botResponse(message:String){
        val timeStamp = Time.timeStamp()

        Global


    }




}
package chaira.carlos.myapplication.utils

import chaira.carlos.myapplication.utils.Constans.OPEN_GOOGLE
import chaira.carlos.myapplication.utils.Constans.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {
    fun basicResponses(_message:String): String{
        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when{
            //Flips a coin
            message.contains("flip") && message.contains("coin")->{
                val r=(0..1).random()
                val result = if (r==0) "heads" else "tails"
                "I flipped a coin and in landed on $result"
            }
            message.contains("solve")->{
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"
                } catch (e: Exception){
                    "Sorry, I can´t solve that."
                }
            }
            message.contains("hello")->{
                when(random){
                    0-> "Hello there!"
                    1-> "Sup"
                    2-> "Buongiorno!"
                    else-> "error"
                }
            }
            message.contains("how are you")->{
                when (random){
                    0-> "I´m doing fine, thanks!"
                    1-> "I´m hungry..."
                    2-> "Pretty good! How about you?"
                    else-> "error"
                }

            }
            message.contains("time") && message.contains("?")->{
                val timestamp = Timestamp(System.currentTimeMillis())
                val sdf= SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timestamp.time))
                date.toString()
            }
            message.contains("open") && message.contains("google")->{
                OPEN_GOOGLE
            }
            message.contains("search")->{
                OPEN_SEARCH
            }
            else->{
                when (random){
                    0->"I don´t understand..."
                    0->"Ttry asking me something different"
                    0->"Idk"
                    else->"error"
                }

            }

        }


    }


}
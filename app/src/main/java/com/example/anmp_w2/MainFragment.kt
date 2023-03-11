package com.example.anmp_w2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDate
import kotlin.random.Random


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //Set true answer
    var trueAnswer = 0;

    //Set score
    var playerScore = 0;

    //function to random integers
    fun randomInt(): Array<Any> {
        var randFirstNumber = Random.nextInt(1,99)
        var randSecondNumber = Random.nextInt(1,199)

        return arrayOf(randFirstNumber, randSecondNumber)
    }

    fun answerChecker(userAnswer:Int, trueAnswer:Int): Boolean{

        if(userAnswer == trueAnswer){
            return true
        }
        else{
            return false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startBtn = view.findViewById<Button>(R.id.btnStart)
        val txtView = view.findViewById<EditText>(R.id.txtName)
//        startBtn.setOnClickListener{
//            val playerName = txtView.text.toString()
//            val action = MainFragmentDirections.actionGameFragment(playerName)
//            Navigation.findNavController(it).navigate(action)
//        }



        //Set and Call random integer function
        var (randFirstNumber, randSecondNumber) = randomInt()

        val firstNum = view.findViewById<TextView>(R.id.txtFirstNumber)
        val secondNum = view.findViewById<TextView>(R.id.txtSecondNumber)

        firstNum.text = randFirstNumber.toString()
        secondNum.text = randSecondNumber.toString()

        trueAnswer = Integer.parseInt(firstNum.text.toString()) + Integer.parseInt(secondNum.text.toString())

        //User answer input
        var userAnswer = view.findViewById<TextInputEditText>(R.id.txtAnswer)

        //Button check answer
        val checkAnswer = view.findViewById<Button>(R.id.btnAnswer)

        checkAnswer.setOnClickListener{
            //Call answerChecker function
            if(answerChecker(Integer.parseInt(userAnswer.text.toString()), trueAnswer)){
                Log.d("True", "Jawaban benar")
                playerScore ++;
                Log.d("Current Score", playerScore.toString())

                //After add score, random again and set it
                var (randFirstNumber, randSecondNumber) = randomInt()

                val firstNum = view.findViewById<TextView>(R.id.txtFirstNumber)
                val secondNum = view.findViewById<TextView>(R.id.txtSecondNumber)

                firstNum.text = randFirstNumber.toString()
                secondNum.text = randSecondNumber.toString()

                trueAnswer = Integer.parseInt(firstNum.text.toString()) + Integer.parseInt(secondNum.text.toString())

            }
            else{
                Log.d("Result", "Jawaban salah")
                val playerName = txtView.text.toString()
                val finalScore = playerScore.toString()
                val action = MainFragmentDirections.actionGameFragment(playerName, playerScore)
                Navigation.findNavController(it).navigate(action)
            }
        }

    }

    class MainActivity : AppCompatActivity() {
        private lateinit var navController: NavController
    }

}


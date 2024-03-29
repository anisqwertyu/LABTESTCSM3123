package com.example.labtestcsm3123


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        var userAns: EditText = findViewById(R.id.answer)
        var checkBtn: Button = findViewById(R.id.checkButton)
        var pointObtained: TextView = findViewById(R.id.points)
        var level: RadioGroup = findViewById(R.id.levelGroup)
        var val1: TextView = findViewById(R.id.num1)
        var val2: TextView = findViewById(R.id.num2)
        var opr: TextView = findViewById(R.id.operator)


        level.setOnCheckedChangeListener { buttonView, isChecked ->     //record selected radio buttons
            val lv = when (level.checkedRadioButtonId) {
                R.id.i3 -> 1
                R.id.i5 -> 2
                R.id.i7 -> 3
                else -> 0
            }
            val1.setText(getRandomNumber(lv).toString())  //set first random value

            var randomOpt = (0..3).shuffled().last()

            if (randomOpt == 0) {
                opr.setText("+")
            } else if (randomOpt == 1) {
                opr.setText("-")
            } else if (randomOpt == 2) {
                opr.setText("*")
            } else{
                opr.setText("/")
            }
            val2.setText(getRandomNumber(lv).toString())  //set second random value
        }




        var pointAccumulate = 0

        checkBtn.setOnClickListener(object : View.OnClickListener {     //detect click on button
            override fun onClick(view: View?) {
                if(verifyAnswer(opr.getText().toString(),
                        userAns.getText().toString().toInt(),
                        val1.getText().toString().toInt(),
                        val2.getText().toString().toInt())){
                    ++pointAccumulate   //increase points
                }else {
                    --pointAccumulate   //reduce points
                }
                pointObtained.setText("Point: "+pointAccumulate)
            }

        })

    }



    private fun getRandomNumber(level: Int): Int{       //get random number
        var random = 0
        if(level == 1){   //i3
            random = (0..9).shuffled().last()  //generate 1 digit
        } else if(level == 2){  //i5
            random = (10..99).shuffled().last()  //generate 2 digits
        }else if(level == 3){   //i7
            random = (100..999).shuffled().last()  //generate 3 digits
        }else{
            random = 0
        }
        return random
    }

    private fun verifyAnswer(operator: String, userAns: Int, val1: Int, val2: Int): Boolean{
        var ans = 0
        if(operator == "+"){
            ans = val1 + val2
        } else if(operator == "-"){
            ans = val1 - val2
        } else if(operator == "*"){
            ans = val1 * val2
        }else if(operator == "/"){
            ans = val1 / val2
        }
        if(userAns == ans){
            return true
        }else{
            return false
        }
    }

}
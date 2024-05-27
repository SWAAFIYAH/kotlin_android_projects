package com.example.my_calculator_app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.my_calculator_app.databinding.ActivityMainBinding
import com.udojava.evalex.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var expression: TextView
    private lateinit var results: TextView
    private lateinit var buttonAC: Button
    private lateinit var buttonX: Button
    private lateinit var buttonplusminus: Button
    private lateinit var buttonDivide: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var buttonMultiply: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var buttonMinus: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var buttonPlus: Button
    private lateinit var buttonPercent: Button
    private lateinit var button0: Button
    private lateinit var buttonDot: Button
    private lateinit var buttonEqual: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        expression = binding.edittext1
        results = binding.edittext2

        // Assign Buttons
        buttonAC = binding.buttonAC
        buttonX = binding.buttonx
        buttonplusminus = binding.buttonplusminus
        buttonDivide = binding.buttondivide
        button1 = binding.button1
        button2 = binding.button2
        button3 = binding.button3
        buttonMultiply = binding.buttontimes
        button4 = binding.button4
        button5 = binding.button5
        button6 = binding.button6
        buttonMinus = binding.buttonminus
        button7 = binding.button7
        button8 = binding.button8
        button9 = binding.button9
        buttonPlus = binding.buttonplus
        buttonPercent = binding.buttonmodulus
        button0 = binding.button0
        buttonDot = binding.buttonpoint
        buttonEqual = binding.buttonequal


        expression.isActivated = true
        expression.isPressed = true

        buttonAC.setOnClickListener { expression.text = "" }
        buttonX.setOnClickListener {
            if (expression.text.isNotEmpty()) {
                val currentText = expression.text.toString()
                val newText = currentText.substring(0, currentText.length - 1)
                expression.text = newText
            }
        }
        buttonplusminus.setOnClickListener {
            val currentText = expression.text.toString()


            if (currentText.isNotEmpty() && currentText.startsWith("-")) {
                // Remove the negative sign from the expression
                val newText = currentText.substring(1)
                expression.text = newText
            } else {
                // Add a negative sign to the beginning of the expression
                expression.text = "-$currentText"
            }
        }
        buttonDivide.setOnClickListener { expression.append("/") }
        button1.setOnClickListener { expression.append("1") }
        button2.setOnClickListener { expression.append("2") }
        button3.setOnClickListener { expression.append("3") }
        buttonMultiply.setOnClickListener { expression.append("*") }
        button4.setOnClickListener { expression.append("4") }
        button5.setOnClickListener { expression.append("5") }
        button6.setOnClickListener { expression.append("6") }
        buttonMinus.setOnClickListener { expression.append("-") }
        button7.setOnClickListener { expression.append("7") }
        button8.setOnClickListener { expression.append("8") }
        button9.setOnClickListener { expression.append("9") }
        buttonPlus.setOnClickListener { expression.append("+") }
        buttonPercent.setOnClickListener { expression.append("%") }
        button0.setOnClickListener { expression.append("0") }
        buttonDot.setOnClickListener { expression.append(".") }
        buttonEqual.setOnClickListener {
            val expressionText = expression.text.toString()

            try {
                val result = calculateExpression(expressionText)


                results.text = result.toString()

            } catch (e: ArithmeticException) {
                // Handle division by zero error
                results.text = "Error: Cannot divide by zero"
            } catch (e: IllegalArgumentException) {
                // Handle invalid expression error
                results.text = "Error: invalid expression"
            }
        }
    }
}
private fun calculateExpression(expressionText: String): String {
    return try {
        val expression = Expression(expressionText)
        expression.eval().toPlainString()
    } catch (e: Exception) {
        "Error"
    }
}

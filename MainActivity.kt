package com.example.jakubkowalwtorek14

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TableLayout
import java.lang.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settings()
    }

    private fun settings() {
        txtView = findViewById(R.id.display)
        ops = findViewById(R.id.ops)
        cal = Calculator(txtView)

        cal.clear()
        for (button in ops.touchables) {
            button.setOnClickListener(this)
        }
    }
    private lateinit var ops: TableLayout
    private lateinit var txtView: TextView
    private lateinit var cal: Calculator

    override fun onClick(view: View) {
        try {
            when (view.id) {
                R.id.decimalButton -> {
                    cal.enterNumber('.')
                }
                R.id.button_1 -> {
                    cal.enterNumber('1')
                }
                R.id.button_2 -> {
                    cal.enterNumber('2')
                }
                R.id.button_3 -> {
                    cal.enterNumber('3')
                }
                R.id.button_4 -> {
                    cal.enterNumber('4')
                }
                R.id.button_5 -> {
                    cal.enterNumber('5')
                }
                R.id.button_6 -> {
                    cal.enterNumber('6')
                }
                R.id.button_7 -> {
                    cal.enterNumber('7')
                }
                R.id.button_8 -> {
                    cal.enterNumber('8')
                }
                R.id.button_9 -> {
                    cal.enterNumber('9')
                }
                R.id.add -> {
                    cal.enterSign('+')
                }
                R.id.subtract -> {
                    cal.enterSign('-')
                }
                R.id.multiply -> {
                    cal.enterSign('*')
                }
                R.id.divide -> {
                    cal.enterSign('/')
                }
                R.id.equal -> {
                    cal.evaluate()
                }
                R.id.buttonClear -> {
                    cal.clear()
                }
            }
        } catch (e: Exception) {
            cal.clear()
            txtView.text = "ERROR incorrect data"
        }
    }

}
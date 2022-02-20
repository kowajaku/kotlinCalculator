//Jakub Kowal wtorek 14
//wykonane wszystkie podpunkty oprócz 5 i 8

package com.example.jakubkowalwtorek14

import android.widget.TextView
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*


class Calculator(var display: TextView) : CalculatorInterface {
    private var isEnteringNumber = false
    private val options = "-+/*"
    private var symbols = listOf<String>()
        set(value: List<String>) {

            when (value.isEmpty()) {
                true -> display.text = "0"
                false -> display.text = value.joinToString(" ")
            }

            field = value
        }

    override fun enterSign(token: Char) {
        isEnteringNumber = false
        symbols += token.toString()
    }
    override fun enterNumber(digit: Char) {
        if (!isEnteringNumber) {
            isEnteringNumber = true
            symbols += digit.toString()
            return
        }
        symbols = symbols.dropLast(1) + "${symbols[symbols.lastIndex]}$digit"
    }
    override fun evaluate() {
        if (symbols.isEmpty()) return
        val s = Stack<Float>()

        symbols = symbols.dropLastWhile { options.indexOf(it) !== -1 }

        for (token in ToPostfix()) {
            val idx = options.indexOf(token)
            if (idx == -1) {
                s.push(token.toFloat())
            } else {
                val right = s.pop()
                val left = s.pop()
                when (token) {
                    "-" -> s.push(left - right)
                    "+" -> s.push(left + right)
                    "*" -> s.push(left * right)
                    "/" -> s.push(left / right)
                }
            }
        }

        symbols = mutableListOf(decimal(s.pop().toDouble()))
    }


    override fun clear() {
        isEnteringNumber = false
        symbols = mutableListOf()
    }
    private fun decimal(num: Double): String {
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.CEILING
        return df.format(num)
    }


    private fun ToPostfix(): MutableList<String> {
        var parsed = mutableListOf<String>()
        val s = Stack<Int>()

        for (token in symbols) {
            if (token.isEmpty()) continue
            val c = token[0]
            val i = options.indexOf(c)

            if (i != - 1) {
                if (s.isEmpty()) {
                    s.push(i)
                }
                else {
                    while (!s.isEmpty()) {
                        val p1 = i / 2
                        val p2 = s.peek() / 2

                        if (p2 > p1 || (p2 == p1 && c != '^')) {
                            parsed.add("${options[s.pop()]}")
                        }
                        else break
                    }
                    s.push(i)
                }
            }
            else {
                parsed.add(token)
            }
        }

        while (!s.isEmpty()) parsed.add("${options[s.pop()]}")
        return parsed
    }


}
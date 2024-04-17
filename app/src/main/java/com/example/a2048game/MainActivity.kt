package com.example.a2048game

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tilesMap2 = mutableMapOf<Int, String>(
            R.id.tile11 to "1", R.id.tile12 to "2", R.id.tile13 to "3", R.id.tile14 to "4",
            R.id.tile21 to "5", R.id.tile22 to "6", R.id.tile23 to "7", R.id.tile24 to "8",
            R.id.tile31 to "9", R.id.tile32 to "10", R.id.tile33 to "11", R.id.tile34 to "12",
            R.id.tile41 to "13", R.id.tile42 to "14", R.id.tile43 to "15", R.id.tile44 to "",
        )

        val valuesList = tilesMap2.values.toMutableList()

        // Shuffle the list of values
        valuesList.shuffle()

        // Update the map with shuffled values
        tilesMap2.replaceAll { _, _ -> valuesList.removeAt(0) }


        tilesMap2.forEach { (key, value) ->
            println("$key -> $value")
            val textView = findViewById<TextView>(key)
            findViewById<TextView>(key).setText(value)
            if (value == "") {
                textView.text = value
                textView.setBackgroundColor(Color.parseColor("#95877C"))
            } else textView.setBackgroundColor(Color.parseColor("#DDC6B4"))

            findViewById<TextView>(key).setText(value)

        }


        // Button logic
        val down = findViewById<Button>(R.id.buttonDown)
        val right = findViewById<Button>(R.id.buttonRight)
        val left = findViewById<Button>(R.id.buttonLeft)
        val up = findViewById<Button>(R.id.buttonUp)



        down.setOnClickListener {
            tilesMap2.forEach { (key, value) ->
                if (value == "") {
                    val textView = findViewById<TextView>(key)
                    textView.text = value
                    textView.setBackgroundColor(Color.parseColor("#DDC6B4"))
                    val currentIndex = tilesMap2.entries.indexOfFirst { it.key == key }
                    println(currentIndex)
                    val newKey = tilesMap2.keys.elementAt(currentIndex - 4)

                    val upperTView = findViewById<TextView>(newKey)
                    upperTView.setBackgroundColor(Color.parseColor("#95877C"))
                    findViewById<TextView>(key).setText(tilesMap2[newKey])
                    val upperValue = tilesMap2.values.elementAt(currentIndex - 4)

                    tilesMap2[key] = upperValue
                    upperTView.text = ""
                    tilesMap2[newKey] = ""
                }

            }


        }
        right.setOnClickListener {
            var count = 0
            tilesMap2.forEach { (key, value) ->
                if (value == "" && count != 0 && count != 4 && count != 8 && count != 12) {
                    val currentIndex = tilesMap2.entries.indexOfFirst { it.key == key }
                    val keyToLeft = tilesMap2.keys.elementAt(currentIndex - 1)
                    val valueToLeft = tilesMap2.values.elementAt(currentIndex - 1)
                    println(valueToLeft)
                    tilesMap2[key] = valueToLeft
                    tilesMap2[keyToLeft] = ""

                    val textView = findViewById<TextView>(key)
                    textView.setText(tilesMap2[key])
                    textView.setBackgroundColor(Color.parseColor("#DDC6B4"))

                    val textView2 = findViewById<TextView>(keyToLeft)
                    textView2.setText("")
                    textView2.setBackgroundColor(Color.parseColor("#95877C"))
                }
                count++
            }


        }
        left.setOnClickListener {

            val tilesMapReversed: MutableMap<Int, String> = mutableMapOf()
            tilesMap2.entries.reversed().forEach { (key, value) ->
                tilesMapReversed[key] = value
            }

            var count = tilesMapReversed.size
            tilesMapReversed.forEach { (key, value) ->
                if (tilesMapReversed[key] == "") {
                    val currentIndex = tilesMapReversed.entries.indexOfFirst { it.key == key }
                    val keyToRight = tilesMapReversed.keys.elementAt(currentIndex - 1)
                    val valueToRight = tilesMapReversed.values.elementAt(currentIndex - 1)
                    // println(valueToRight)
                    tilesMapReversed[key] = valueToRight
                    tilesMapReversed[keyToRight] = ""

                    val textView = findViewById<TextView>(key)
                    textView.setText(tilesMapReversed[key])
                    textView.setBackgroundColor(Color.parseColor("#DDC6B4"))

                    val textView2 = findViewById<TextView>(keyToRight)
                    textView2.setText("")
                    textView2.setBackgroundColor(Color.parseColor("#95877C"))
                }
                count--
            }
            var iter = tilesMap2.size
            for ((key, value) in tilesMapReversed) {
                tilesMap2[key] = value
            }
        }

        up.setOnClickListener {
            var count = 0
            tilesMap2.forEach { (key, value) ->
                if (count > 3) {
                    val currentIndex = tilesMap2.entries.indexOfFirst { it.key == key }
                    val emptyKey = tilesMap2.keys.elementAt(currentIndex - 4)
                    if (tilesMap2[emptyKey] == "") {
                        println(value)
                        tilesMap2[emptyKey] = value
                        tilesMap2[key] = ""

                        val textView = findViewById<TextView>(key)
                        textView.setText("")
                        textView.setBackgroundColor(Color.parseColor("#95877C"))

                        val textView2 = findViewById<TextView>(emptyKey)
                        textView2.setText(value)
                        textView2.setBackgroundColor(Color.parseColor("#DDC6B4"))
                    }
                }
                count++
            }

        }


    }




}
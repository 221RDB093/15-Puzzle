package com.example.a2048game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import java.util.Scanner
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val down = findViewById<Button>(R.id.buttonDown)
        val right = findViewById<Button>(R.id.buttonRight)
        val left = findViewById<Button>(R.id.buttonLeft)
        val up = findViewById<Button>(R.id.buttonUp)


        down.setOnClickListener {
            Toast.makeText(this, "Shift Down", Toast.LENGTH_SHORT).show()
        }
        right.setOnClickListener {
            Toast.makeText(this, "Shift Right", Toast.LENGTH_SHORT).show()
        }
        left.setOnClickListener {
            Toast.makeText(this, "Shift Left", Toast.LENGTH_SHORT).show()
        }
        up.setOnClickListener {
            Toast.makeText(this, "Shift Up", Toast.LENGTH_SHORT).show()
        }

    }


}
package com.example.a2048game

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private val tiles = intArrayOf(
        R.id.tile11, R.id.tile12, R.id.tile13, R.id.tile14,
        R.id.tile21, R.id.tile22, R.id.tile23, R.id.tile24,
        R.id.tile31, R.id.tile32, R.id.tile33, R.id.tile34,
        R.id.tile41, R.id.tile42, R.id.tile43, R.id.tile44,
    )

    private fun generateNewTile() {
        var gameover = false
        // TODO: Implement game over
        while(true) {
            val tile1 = tiles[Random.nextInt(tiles.size)]
            val tileClr = findViewById<ImageView>(tile1).getDrawable()
            if (tileClr == null) {
                val chosen = findViewById<ImageView>(tile1)
                chosen.setImageResource(R.drawable.tile2)
                break

            } // else if (tileClr != null) {
//                var iterator = 0
//                for (tile in tiles) {
//                    if (iterator != tiles.size) {
//                        val nextTile = findViewById<ImageView>(tile).getDrawable()
//                        if (nextTile != null) {
//                            iterator++
//                        }
//                    } else {
//                        println("Game Over!!!")
//                        gameover = true
//                        break
//                    }
//                    break
//                }
//
//            }
            else {
                continue
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Atsauce: https://www.baeldung.com/kotlin/list-get-random-item
        val idx1 = Random.nextInt(tiles.size)
        var idx2 = Random.nextInt(tiles.size)
        while(idx1 == idx2)  idx2 = Random.nextInt(tiles.size);
        val randomTile1 = tiles[idx1]
        val randomTile2 = tiles[idx2]

        val t1 = findViewById<ImageView>(randomTile1)
        val t2 = findViewById<ImageView>(randomTile2)
        t1.setImageResource(R.drawable.tile2)
        t2.setImageResource(R.drawable.tile2)

        // Button logic
        val down = findViewById<Button>(R.id.buttonDown)
        val right = findViewById<Button>(R.id.buttonRight)
        val left = findViewById<Button>(R.id.buttonLeft)
        val up = findViewById<Button>(R.id.buttonUp)


        down.setOnClickListener {
            Toast.makeText(this, "Shift Down", Toast.LENGTH_SHORT).show()
            // val some = findViewById<ImageView>(R.id.tile)
            // TODO: create shift down operation
            generateNewTile()

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
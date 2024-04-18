package com.example.a2048game

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Šī karte satur Int tipa atslēgas, kuras ir faila 'activity_main.xml' TextView identifikatori.
        Katrs no tiem satur String tipa vērtību, kura norāda uz flīzes uzrakstīto skaitli.
        Sākumā flīzes sakārtotas pareizajā jeb uzvaru neesošā secībā un pēc tam tās tiek sajauktas.
         */
        var tilesMap = mutableMapOf<Int, String>(
            R.id.tile11 to "1", R.id.tile12 to "2", R.id.tile13 to "3", R.id.tile14 to "4",
            R.id.tile21 to "5", R.id.tile22 to "6", R.id.tile23 to "7", R.id.tile24 to "8",
            R.id.tile31 to "9", R.id.tile32 to "10", R.id.tile33 to "11", R.id.tile34 to "12",
            R.id.tile41 to "13", R.id.tile42 to "14", R.id.tile43 to "15", R.id.tile44 to "",
        )

        // Lai 'samaisītu' flīzes, 'tilesMap' vispirms ir jāpārveido par sarakstu.
        val valuesList = tilesMap.values.toMutableList()
        // Chat GPT vaicājums  kartes sajaukšanai:
        // I have created a map in Kotlin: val tilesMap2 = mutableMapOf<Int, String>.
        // How can I shuffle the values for each key?

        // Saraksts tiek samaisīts
        valuesList.shuffle()

        // Atjauno karti tilesMap ar sajauktajām vērtībām
        tilesMap.replaceAll { _, _ -> valuesList.removeAt(0) }


        // Front-end daļas pielīdzināšana iepriekš sajauktajai kartei
        tilesMap.forEach { (key, value) ->
            println("$key -> $value")
            val textView = findViewById<TextView>(key)
            findViewById<TextView>(key).setText(value)
            if (value == "") {
                textView.text = value
                textView.setBackgroundColor(Color.parseColor("#6A22E9"))
            } else textView.setBackgroundColor(Color.parseColor("#FFE705"))

            findViewById<TextView>(key).setText(value)

        }


        // Pogu sasaiste ar front-end daļu
        val down = findViewById<Button>(R.id.buttonDown)
        val right = findViewById<Button>(R.id.buttonRight)
        val left = findViewById<Button>(R.id.buttonLeft)
        val up = findViewById<Button>(R.id.buttonUp)

        // Tālāk: visu četru pogu funkcijas

        down.setOnClickListener {
            var count = 0
            tilesMap.forEach { (key, value) ->

                // Bez šī if-a spēle 'nobruks', ja tukšā vērtība atradīsies kādā no pirmajām četrām pozīcijām
                // Ideālā kārtā cikls būtu kjāpārveido, lai tas darbu sāk no ceturtā locekļa. Tagad
                // sanāk, ka tas izpilda četras iterācijas 'pa tukšo'.
                if (count > 3) {

                    // Atrod tukšo lauku
                    if (value == "") {

                        val textView = findViewById<TextView>(key)
                        textView.text = value
                        textView.setBackgroundColor(Color.parseColor("#FFE705"))

                        // Atrod indeksu tam, kurš pēc kārtas sanāk šis loceklis kartē.
                        val currentIndex = tilesMap.entries.indexOfFirst { it.key == key }

                        // Atrod elementu, kas atrodas tieši virs tukšā lauciņa.
                        val newKey = tilesMap.keys.elementAt(currentIndex - 4)

                        val upperTView = findViewById<TextView>(newKey)
                        upperTView.setBackgroundColor(Color.parseColor("#6A22E9"))
                        findViewById<TextView>(key).setText(tilesMap[newKey])
                        val upperValue = tilesMap.values.elementAt(currentIndex - 4)

                        tilesMap[key] = upperValue
                        upperTView.text = ""
                        tilesMap[newKey] = ""
                    }
                }
                count++
            }


        }
        right.setOnClickListener {
            var count = 0
            tilesMap.forEach { (key, value) ->
                if (value == "" && count != 0 && count != 4 && count != 8 && count != 12) {
                    val currentIndex = tilesMap.entries.indexOfFirst { it.key == key }

                    // Atrod elementu, kas atrodas vienu lauciņu iepriekš.
                    // No šī elementa tukšais lauciņš iegūs savu nākamo vērtību.
                    val keyToLeft = tilesMap.keys.elementAt(currentIndex - 1)
                    val valueToLeft = tilesMap.values.elementAt(currentIndex - 1)

                    tilesMap[key] = valueToLeft
                    tilesMap[keyToLeft] = ""

                    // Front-end daļa
                    val textView = findViewById<TextView>(key)
                    textView.setText(tilesMap[key])
                    textView.setBackgroundColor(Color.parseColor("#FFE705"))

                    val textView2 = findViewById<TextView>(keyToLeft)
                    textView2.setText("")
                    textView2.setBackgroundColor(Color.parseColor("#6A22E9"))
                }
                count++
            }


        }
        /*
        Ar šo funkciju radās pirmās nopietnās problēmas. Sākumā biju domājis izmantot funkcijā 'right'
        izmantoto metodi iegūstot 'keyToRight' mainīgo pieskaitot 1 pie currentIndex, taču problēma
        ir tajā, ka nākamajā iterācijā dators joprojām izmanto to karti, kas viņam bija sākumā.
        Respektīvi, pat ja esmu nomainījis atslēgas keyToRight vērtību uz tukšu, tas joprojām
        atcerēsies iepriekšējo vērtību. Manis izdomātais risinājums ir izveidot karti, kuras vērtības
        ir izvietotas ačgārni tilesMap vērtībām. Tādā veidā algoritms jau kā iepriekš varētu nolasīt
        vajadzīgo vērtību 'atkāpjoties' (jeb no curentIndex atņemot nevis pieskaitot skaitli).
         Beigās, protams ačgārnās kartes vērtības tiek pielīdzinātas kartei tilesMap.
         */

        left.setOnClickListener {

            val tilesMapReversed: MutableMap<Int, String> = mutableMapOf()
            tilesMap.entries.reversed().forEach { (key, value) ->
                tilesMapReversed[key] = value
            }

            var count = 0 // tilesMapReversed.size
            tilesMapReversed.forEach { (key, value) ->

                    if (tilesMapReversed[key] == "") {

                        val currentIndex = tilesMapReversed.entries.indexOfFirst { it.key == key }
                        val keyToRight = tilesMapReversed.keys.elementAt(currentIndex - 1)
                        val valueToRight = tilesMapReversed.values.elementAt(currentIndex - 1)

                        tilesMapReversed[key] = valueToRight
                        tilesMapReversed[keyToRight] = ""

                        // Front-end
                        val textView = findViewById<TextView>(key)
                        textView.setText(tilesMapReversed[key])
                        textView.setBackgroundColor(Color.parseColor("#FFE705"))

                        val textView2 = findViewById<TextView>(keyToRight)
                        textView2.setText("")
                        textView2.setBackgroundColor(Color.parseColor("#6A22E9"))
                    }
                    count++
            }

            // tilesMapReversed vērtību pielīdzināšana tilesMap kartei.
            for ((key, value) in tilesMapReversed) {
                tilesMap[key] = value
            }
        }

        up.setOnClickListener {
//            var count = 0
//            tilesMap.forEach { (key, value) ->
//                if (count > 3) {
//                    val currentIndex = tilesMap.entries.indexOfFirst { it.key == key }
//                    val emptyKey = tilesMap.keys.elementAt(currentIndex - 4)
//                    if (tilesMap[emptyKey] == "") {
//                        println(value)
//                        tilesMap[emptyKey] = value
//                        tilesMap[key] = ""
//
//                        val textView = findViewById<TextView>(key)
//                        textView.setText("")
//                        textView.setBackgroundColor(Color.parseColor("#6A22E9"))
//
//                        val textView2 = findViewById<TextView>(emptyKey)
//                        textView2.setText(value)
//                        textView2.setBackgroundColor(Color.parseColor("#FFE705"))
//                    }
//                }
//                count++
//            }

            val tilesMapReversed: MutableMap<Int, String> = mutableMapOf()
            tilesMap.entries.reversed().forEach { (key, value) ->
                tilesMapReversed[key] = value
            }

            var count = 0 // tilesMapReversed.size
            tilesMapReversed.forEach { (key, value) ->

                if (tilesMapReversed[key] == "") {

                    val currentIndex = tilesMapReversed.entries.indexOfFirst { it.key == key }
                    val lowerKey = tilesMapReversed.keys.elementAt(currentIndex - 4)
                    val lowerValue = tilesMapReversed.values.elementAt(currentIndex - 4)

                    tilesMapReversed[key] = lowerValue
                    tilesMapReversed[lowerKey] = ""

                    // Front-end
                    val textView = findViewById<TextView>(key)
                    textView.setText(tilesMapReversed[key])
                    textView.setBackgroundColor(Color.parseColor("#FFE705"))

                    val textView2 = findViewById<TextView>(lowerKey)
                    textView2.setText("")
                    textView2.setBackgroundColor(Color.parseColor("#6A22E9"))
                }
                count++
            }

            // tilesMapReversed vērtību pielīdzināšana tilesMap kartei.
            for ((key, value) in tilesMapReversed) {
                tilesMap[key] = value
            }

        }


    }




}
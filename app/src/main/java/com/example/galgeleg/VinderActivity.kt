package com.example.galgeleg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_vinder.*

class VinderActivity : AppCompatActivity() {

    var galgelogik: Galgelogik = Galgelogik()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vinder)

        val tries = intent.getIntExtra("Tries", 0)
        tryCount_text.text = galgelogik.antalForkerteBogstaver.toString() + tries

        recyclerView_highScore.layoutManager = LinearLayoutManager(this)
        recyclerView_highScore.adapter = HighscoreAdapter()
    }
}
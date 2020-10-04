package com.example.galgeleg

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_vinder.*

class VinderActivity : AppCompatActivity() {

    var galgelogik: Galgelogik = Galgelogik()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vinder)

        val tries = intent.getIntExtra("Tries", 0)
        tryCount_text.text = galgelogik.antalForkerteBogstaver.toString() + tries
    }
}
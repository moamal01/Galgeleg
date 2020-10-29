package com.example.galgeleg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val galgelogik: Galgelogik = Galgelogik()
    var word = galgelogik.ordet


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sæt onClickListener
        gæt_button.setOnClickListener(this)

        galgelogik.startNytSpil()

        word_textView.text = galgelogik.synligtOrd
    }

    override fun onClick(view: View?) {
        val bogstav: String = letter_guess.text.toString().toLowerCase(Locale.ROOT)

        galgelogik.gætBogstav(bogstav)
        gættede_bogstav_textView.text = galgelogik.brugteBogstaver.toString()

        updateHangman()
        onEndGame()
    }

    private fun updateHangman() {
        when (galgelogik.antalForkerteBogstaver) {
            1 -> hangman_imageView.setImageResource(R.drawable.forkert1)
            2 -> hangman_imageView.setImageResource(R.drawable.forkert2)
            3 -> hangman_imageView.setImageResource(R.drawable.forkert3)
            4 -> hangman_imageView.setImageResource(R.drawable.forkert4)
            5 -> hangman_imageView.setImageResource(R.drawable.forkert5)
            6 -> hangman_imageView.setImageResource(R.drawable.forkert6)
        }
    }

    private fun onEndGame() {
        if (galgelogik.erSpilletVundet()) {
            val intent = Intent(this, VinderActivity::class.java)
            intent.putExtra("Tries", galgelogik.antalForkerteBogstaver)
            startActivity(intent)
            finish()
        } else if (galgelogik.erSpilletTabt()) {
            val intent = Intent(this, TaberActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
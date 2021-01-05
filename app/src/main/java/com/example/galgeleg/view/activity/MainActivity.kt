package com.example.galgeleg.view.activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.galgeleg.R
import com.example.galgeleg.controller.Galgelogik
import com.example.galgeleg.model.Player
import com.example.galgeleg.view.fragment.ChooseWordFragment
import com.example.galgeleg.view.fragment.HighScoreFragment
import kotlinx.android.synthetic.main.activity_menu.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val galgelogik = Galgelogik.getInstance()
    private val bgThread = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        galgelogik.enterPressed(enter_name_menu, start_game_button)

        start_game_button.setOnClickListener(this)
        custom_word.setOnClickListener(this)
        highScore_button_menu.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        bgThread.execute {
            galgelogik.hentOrdFraDr()
        }
    }

    private fun openFragment(fragment: Fragment) {

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_menu, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onClick(view: View?) {

        when (view) {
            start_game_button -> {
                val player = Player(enter_name_menu.text.toString())

                if (player.name == "") {
                    error_message_menu.text = "Indtast venligst et navn"
                } else {
                    val intent = Intent(this, GameActivity::class.java)
                    intent.putExtra("name", player.name)
                    galgelogik.startNytSpil(null)
                    startActivity(intent)
                }
            }
            highScore_button_menu -> {
                val highScoreFragment = HighScoreFragment()
                openFragment(highScoreFragment)
            }
            custom_word -> {
                val chooseWordFragment = ChooseWordFragment()
                openFragment(chooseWordFragment)
            }
        }
    }
}
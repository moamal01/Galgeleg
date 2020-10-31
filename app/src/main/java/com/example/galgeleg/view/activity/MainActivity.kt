package com.example.galgeleg.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.galgeleg.R
import com.example.galgeleg.model.Galgelogik
import com.example.galgeleg.model.Player
import kotlinx.android.synthetic.main.activity_menu.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val galgelogik = Galgelogik.getInstance()
    private val bgThread = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        start_game_button.setOnClickListener(this)

        bgThread.execute {
            galgelogik.hentOrdFraDr()
        }
    }

    override fun onClick(p0: View?) {
        var player = Player(enter_name_menu.text.toString())

        if (player.name == "") {
            error_message_menu.text = "Indtast venligst et navn"
        } else {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("name", player.name)
            startActivity(intent)
        }
    }
}
package com.example.galgeleg.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.galgeleg.R
import kotlinx.android.synthetic.main.activity_menu.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        start_game_button.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }
}
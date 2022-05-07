package com.example.k

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.k.logic.Baraja
import com.example.k.logic.Valor

class StartActivity : AppCompatActivity() {
    lateinit var botonNormal : Button
    lateinit var botonPersonalizada : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        botonNormal = findViewById(R.id.botonNormalStart)
        botonPersonalizada = findViewById(R.id.botonPersonalizadaStart)

        botonNormal.setOnClickListener { iniciarPartidaNormal() }
        botonPersonalizada.setOnClickListener { configurarPartida() }
    }

    private fun iniciarPartidaNormal() {
        val gameIntent = Intent(this, GameActivity::class.java).apply {
            putExtra("baraja", Baraja(ArrayList<Valor>()))
        }
        startActivity(gameIntent)
    }

    private fun configurarPartida() {
        val customIntent = Intent(this, CustomNumbersActivity::class.java).apply {

        }
        startActivity(customIntent)
    }
}
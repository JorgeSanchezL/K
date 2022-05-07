package com.example.k

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.k.logic.Baraja
import com.example.k.logic.Carta
import com.example.k.logic.Numero
import com.example.k.logic.Valor

class GameActivity : AppCompatActivity() {

    lateinit var baraja: Baraja
    lateinit var textoAccion: TextView
    lateinit var imagenCarta: ImageView
    lateinit var botonRotar: Button
    lateinit var context: Context
    lateinit var cartas: ArrayList<Carta>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        textoAccion = findViewById(R.id.textoAccion)
        imagenCarta = findViewById(R.id.imagenCarta)
        botonRotar = findViewById(R.id.botonRotar)
        context = imagenCarta.context
        baraja = intent.extras?.getSerializable("baraja") as Baraja
        if (baraja.valores.count() == 0) cartas = baraja.crearBarajaNormal()
        else cartas = baraja.crearBaraja()

        botonRotar.setOnClickListener {
            sacarCarta()
        }
    }

    fun sacarCarta() {
        textoAccion.text = ""
        cartas.shuffle()
        var carta = cartas.removeAt(0)
        darVueltaPrimeraMitad(carta)
    }

    private fun darVueltaPrimeraMitad(carta: Carta) {
        imagenCarta.rotationY = 0f
        imagenCarta.animate().apply {
            imagenCarta.setImageResource(context.resources.getIdentifier("dorso", "drawable", context.packageName))
            interpolator = AccelerateInterpolator()
            duration = 1000
            rotationYBy(90f)
        }.withEndAction(Runnable { darVueltaSegundaMitad(carta) })
    }

    private fun darVueltaSegundaMitad(carta: Carta) {
        imagenCarta.rotationY = -90f
        imagenCarta.animate().apply {
            imagenCarta.setImageResource(context.resources.getIdentifier(carta.getStringImagen(), "drawable", context.packageName))
            interpolator = DecelerateInterpolator()
            duration = 1000
            rotationYBy(90f)
        }
        textoAccion.text = carta.getAccion()
    }
}
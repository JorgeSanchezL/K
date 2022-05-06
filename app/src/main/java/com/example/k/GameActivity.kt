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

    val baraja = crearBarajaNormal()
    lateinit var textoAccion: TextView
    lateinit var imagenCarta: ImageView
    lateinit var botonRotar: Button
    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        textoAccion = findViewById(R.id.textoAccion)
        imagenCarta = findViewById(R.id.imagenCarta)
        botonRotar = findViewById(R.id.botonRotar)
        context = imagenCarta.context

        botonRotar.setOnClickListener {
            sacarCarta()
        }
    }

    fun sacarCarta() {
        textoAccion.text = ""
        baraja.cartas.shuffle()
        var carta = baraja.cartas.removeAt(0)
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

    fun crearBarajaNormal() : Baraja {
        var valores = ArrayList<Valor>()
        valores.add(Valor(Numero.AS, "Cascada"))
        valores.add(Valor(Numero.DOS, "Beben los dos de la derecha"))
        valores.add(Valor(Numero.TRES, "Beben los tres de la izquierda"))
        valores.add(Valor(Numero.CUATRO, "Mandas cuatro tragos a una persona"))
        valores.add(Valor(Numero.CINCO, "Repartes cinco tragos"))
        valores.add(Valor(Numero.SEIS, "Comodin"))
        //valores.add(Valor(Numero.SIETE, "Te libras"))
        valores.add(Valor(Numero.OCHO, "Bebes durante ocho segundos"))
        valores.add(Valor(Numero.NUEVE, "Beben uno si uno no (Empieza el que ha sacado la carta)"))
        valores.add(Valor(Numero.DIEZ, "Votación"))
        valores.add(Valor(Numero.JOTA, "Beben todos"))
        valores.add(Valor(Numero.REY, "Beben los hombres"))
        valores.add(Valor(Numero.REINA, "Beben las mujeres"))
        return Baraja(valores)
    }
}
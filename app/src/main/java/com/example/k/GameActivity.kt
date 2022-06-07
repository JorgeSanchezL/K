package com.example.k

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import android.widget.*
import com.example.k.logic.Baraja
import com.example.k.logic.Carta

class GameActivity : AppCompatActivity() {

    lateinit var baraja: Baraja
    lateinit var textoAccion: TextView
    lateinit var imagenCarta: ImageView
    lateinit var textoFin: TextView
    lateinit var botonReiniciar: Button
    lateinit var botonVolver: Button
    lateinit var context: Context
    lateinit var cartas: ArrayList<Carta>
    lateinit var cartasInicial: ArrayList<Carta>

    var cartaMostrando = false
    var haciendoAccion = false


    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
            .setMessage("¿Estás seguro de querer salir del juego?")
            .setPositiveButton("Sí", DialogInterface.OnClickListener { dialog, which -> finish() })
            .setNegativeButton("No", null)
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        textoAccion = findViewById(R.id.textoAccion)
        imagenCarta = findViewById(R.id.imagenCarta)
        textoFin = findViewById(R.id.textoFin)
        botonReiniciar = findViewById(R.id.botonReiniciar)
        botonVolver = findViewById(R.id.botonVolverDespuesDeFin)

        context = imagenCarta.context
        baraja = intent.extras?.getSerializable("baraja") as Baraja

        if (baraja.valores.count() == 0) cartas = baraja.crearBarajaNormal()
        else cartas = baraja.crearBaraja()

        cartasInicial = ArrayList()
        for (carta in cartas) cartasInicial.add(carta)

        imagenCarta.setOnClickListener {
            clickCarta()
        }

        botonReiniciar.setOnClickListener {
            clickReiniciar()
        }

        botonVolver.setOnClickListener {
            clickVolverMenu()
        }
    }

    private fun clickCarta() {
        if (!haciendoAccion) {
            if (cartaMostrando) {
                quitarCarta()
            } else {
                girarCarta()
            }
        }
    }

    private fun girarCarta() {
        haciendoAccion = true
        textoAccion.text = ""
        cartas.shuffle()
        val carta = cartas.removeAt(0)
        darVueltaPrimeraMitad(carta)
    }

    private fun quitarCarta() {
        textoAccion.text = ""
        haciendoAccion = true
        cartaMostrando = false
        imagenCarta.animate().apply() {
            interpolator = AccelerateInterpolator()
            duration = 500
            translationXBy(800f)
        }.withEndAction {
            if (cartas.size > 0) sacarCarta()
            else mostrarFin()
        }
    }

    private fun sacarCarta() {
        imagenCarta.setImageResource(context.resources.getIdentifier("dorso", "drawable", context.packageName))
        imagenCarta.animate().apply() {
            duration = 0
            translationXBy(-1600f)
        }.withEndAction {
            ponerCarta()
        }
    }

    private fun ponerCarta() {
        imagenCarta.visibility = View.VISIBLE
        imagenCarta.animate().apply() {
            interpolator = DecelerateInterpolator()
            duration = 800
            translationXBy(800f)
        }.withEndAction { haciendoAccion = false }
    }

    private fun darVueltaPrimeraMitad(carta: Carta) {
        imagenCarta.rotationY = 0f
        imagenCarta.animate().apply {
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
        }.withEndAction { haciendoAccion = false; cartaMostrando = true }
        textoAccion.text = carta.getAccion()
    }

    private fun mostrarFin() {
        imagenCarta.visibility = View.INVISIBLE
        textoFin.visibility = View.VISIBLE
        botonReiniciar.visibility = View.VISIBLE
        botonVolver.visibility = View.VISIBLE
    }

    private fun clickVolverMenu() {
        val startIntent = Intent(this, StartActivity::class.java).apply {}
        startIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(startIntent)
    }

    private fun clickReiniciar () {
        textoFin.visibility = View.INVISIBLE
        botonReiniciar.visibility = View.INVISIBLE
        botonVolver.visibility = View.INVISIBLE
        rellenarBaraja()
        sacarCarta()
    }

    private fun rellenarBaraja() {
        for (carta in cartasInicial) {
            cartas.add(carta)
        }
    }
}
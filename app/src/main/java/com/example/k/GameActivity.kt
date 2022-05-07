package com.example.k

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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
    lateinit var context: Context
    lateinit var cartas: ArrayList<Carta>

    lateinit var cartaBaraja1: ImageView
    lateinit var cartaBaraja2: ImageView
    lateinit var cartaBaraja3: ImageView
    lateinit var cartaBaraja4: ImageView
    lateinit var cartaBaraja5: ImageView
    lateinit var cartaBaraja6: ImageView

    var cartaMostrando = false
    var cartaGirando = false
    var numeroCartasInicial: Int = 0


    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
            .setMessage("¿Estás seguro de querer cerrar el juego?")
            .setPositiveButton("Sí", DialogInterface.OnClickListener { dialog, which -> finish() })
            .setNegativeButton("No", null)
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        textoAccion = findViewById(R.id.textoAccion)
        imagenCarta = findViewById(R.id.imagenCarta)

        cartaBaraja1 = findViewById(R.id.imagenBaraja1)
        cartaBaraja2 = findViewById(R.id.imagenBaraja2)
        cartaBaraja3 = findViewById(R.id.imagenBaraja3)
        cartaBaraja4 = findViewById(R.id.imagenBaraja4)
        cartaBaraja5 = findViewById(R.id.imagenBaraja5)
        cartaBaraja6 = findViewById(R.id.imagenBaraja6)

        context = imagenCarta.context
        baraja = intent.extras?.getSerializable("baraja") as Baraja

        if (baraja.valores.count() == 0) cartas = baraja.crearBarajaNormal()
        else cartas = baraja.crearBaraja()

        numeroCartasInicial = cartas.size

        actualizarNumeroCartas()

        imagenCarta.setOnClickListener {
            clickCarta()
        }

        cartaBaraja1.setOnClickListener {
            levantarCarta()
        }

        cartaBaraja2.setOnClickListener {
            levantarCarta()
        }

        cartaBaraja3.setOnClickListener {
            levantarCarta()
        }

        cartaBaraja4.setOnClickListener {
            levantarCarta()
        }

        cartaBaraja5.setOnClickListener {
            levantarCarta()
        }

        cartaBaraja6.setOnClickListener {
            levantarCarta()
        }
    }
    //Falta animar
    private fun levantarCarta() {
        imagenCarta.visibility = View.VISIBLE
        imagenCarta.setImageResource(context.resources.getIdentifier("dorso", "drawable", context.packageName))

    }

    private fun clickCarta() {
        if (!cartaGirando) {
            if (cartaMostrando) {
                quitarCarta()
            } else {
                girarCarta()
            }
        }
    }

    private fun girarCarta() {
        cartaGirando = true
        textoAccion.text = ""
        cartas.shuffle()
        var carta = cartas.removeAt(0)
        darVueltaPrimeraMitad(carta)
    }

    private fun quitarCarta() {
        imagenCarta.visibility = View.INVISIBLE
        textoAccion.text = ""
        imagenCarta.startAnimation(AnimationUtils.loadAnimation(this, R.anim.quitarcarta))
        cartaMostrando = false
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
        }.withEndAction { cartaGirando = false; cartaMostrando = true }
        textoAccion.text = carta.getAccion()
    }

    private fun actualizarNumeroCartas() {
        when {
            cartas.size == 1 -> cartaBaraja1.visibility = View.INVISIBLE
            cartas.size < numeroCartasInicial / 6 || cartas.size == 2 -> cartaBaraja2.visibility = View.INVISIBLE
            cartas.size < numeroCartasInicial / 6 * 2 || cartas.size == 3 -> cartaBaraja3.visibility = View.INVISIBLE
            cartas.size < numeroCartasInicial / 6 * 3 || cartas.size == 4 -> cartaBaraja4.visibility = View.INVISIBLE
            cartas.size < numeroCartasInicial / 6 * 4 || cartas.size == 5 -> cartaBaraja5.visibility = View.INVISIBLE
            cartas.size < numeroCartasInicial / 6 * 5 || cartas.size == 6 -> cartaBaraja6.visibility = View.INVISIBLE
        }
    }

    private fun getPrimeraCartaBarajaVisible(): ImageView {
        when {
            cartaBaraja6.visibility == View.VISIBLE -> return cartaBaraja6
            cartaBaraja5.visibility == View.VISIBLE -> return cartaBaraja5
            cartaBaraja4.visibility == View.VISIBLE -> return cartaBaraja4
            cartaBaraja3.visibility == View.VISIBLE -> return cartaBaraja3
            cartaBaraja2.visibility == View.VISIBLE -> return cartaBaraja2
            else -> return cartaBaraja1
        }
    }
}
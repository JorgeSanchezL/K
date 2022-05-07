package com.example.k

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import com.example.k.logic.Baraja
import com.example.k.logic.Numero
import com.example.k.logic.Valor

class CustomNumbersActivity : AppCompatActivity() {
    lateinit var botonIniciar : Button
    lateinit var botonVolver: Button
    lateinit var botonConfigurarAcciones : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customnumbers)
        botonIniciar = findViewById(R.id.botonIniciarPartidaPersonalizarNumeros)
        botonVolver = findViewById(R.id.botonVolverNumerosPersonalizados)
        botonConfigurarAcciones = findViewById(R.id.botonConfigurarAcciones)

        botonIniciar.setOnClickListener { iniciarPartida() }
        botonVolver.setOnClickListener { finish() }
        botonConfigurarAcciones.setOnClickListener { configurarAcciones() }
    }

    private fun iniciarPartida() {
        val valores = getValoresMarcados()
        if (comprobarMarcadas(valores)) {
            val gameIntent = Intent(this, GameActivity::class.java).apply {
                putExtra("baraja", Baraja(valores))
            }
            startActivity(gameIntent)
        }
    }

    private fun configurarAcciones() {
        val valores = getValoresMarcados()
        if (comprobarMarcadas(valores)) {
            val gameIntent = Intent(this, CustomActionsActivity::class.java).apply {
                putExtra("valores", valores)
            }
            startActivity(gameIntent)
        }
    }

    private fun comprobarMarcadas(valores: ArrayList<Valor>) : Boolean {
        if (valores.size == 0) {
            val alertaNoHayValores = android.app.AlertDialog.Builder(this)
            alertaNoHayValores.setTitle("Error")
            alertaNoHayValores.setMessage("No has marcado ningún valor")
            alertaNoHayValores.show()
            return false
        }
        return true
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun getValoresMarcados() : ArrayList<Valor> {
        var res = ArrayList<Valor>()
        if (findViewById<Switch>(R.id.switchAs).isChecked) res.add(Valor(Numero.AS, ""))
        if (findViewById<Switch>(R.id.switch2).isChecked) res.add(Valor(Numero.DOS, ""))
        if (findViewById<Switch>(R.id.switch3).isChecked) res.add(Valor(Numero.TRES, ""))
        if (findViewById<Switch>(R.id.switch4).isChecked) res.add(Valor(Numero.CUATRO, ""))
        if (findViewById<Switch>(R.id.switch5).isChecked) res.add(Valor(Numero.CINCO, ""))
        if (findViewById<Switch>(R.id.switch6).isChecked) res.add(Valor(Numero.SEIS, ""))
        if (findViewById<Switch>(R.id.switch7).isChecked) res.add(Valor(Numero.SIETE, ""))
        if (findViewById<Switch>(R.id.switch8).isChecked) res.add(Valor(Numero.OCHO, ""))
        if (findViewById<Switch>(R.id.switch9).isChecked) res.add(Valor(Numero.NUEVE, ""))
        if (findViewById<Switch>(R.id.switch10).isChecked) res.add(Valor(Numero.DIEZ, ""))
        if (findViewById<Switch>(R.id.switchJota).isChecked) res.add(Valor(Numero.JOTA, ""))
        if (findViewById<Switch>(R.id.switchReina).isChecked) res.add(Valor(Numero.REINA, ""))
        if (findViewById<Switch>(R.id.switchRey).isChecked) res.add(Valor(Numero.REY, ""))
        for (valor : Valor in res) {
            valor.asignarValorPredeterminado()
        }
        return res
    }
}
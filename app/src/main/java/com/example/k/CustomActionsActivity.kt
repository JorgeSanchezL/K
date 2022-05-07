package com.example.k

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.k.logic.Baraja
import com.example.k.logic.Valor

class CustomActionsActivity : AppCompatActivity() {

    lateinit var listaValores : ArrayList<Valor>
    lateinit var listView : ListView

    lateinit var botonIniciar : Button
    lateinit var botonVolver: Button
    lateinit var botonRestaurarPredeterminadas : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customactions)

        botonIniciar = findViewById(R.id.botonContinuarAccionesPersonalizadas)
        botonVolver = findViewById(R.id.botonVolverAccionesPersonalizadas)
        botonRestaurarPredeterminadas = findViewById(R.id.botonRestaurarAccionesPredeterminadas)

        listaValores = intent.extras?.getSerializable("valores") as ArrayList<Valor>

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaValores)
        listView = findViewById(R.id.listViewActions)
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            showInputDialog(parent.getItemAtPosition(position) as Valor)
        }

        botonIniciar.setOnClickListener { iniciarPartida() }
        botonVolver.setOnClickListener { finish() }
        botonRestaurarPredeterminadas.setOnClickListener { restaurarPredeterminadas() }
    }

    private fun iniciarPartida() {
        val gameIntent = Intent(this, GameActivity::class.java).apply {
            putExtra("baraja", Baraja(listaValores))
        }
        startActivity(gameIntent)
    }

    private fun restaurarPredeterminadas() {
        for (valor : Valor in listaValores) {
            valor.asignarValorPredeterminado()
        }
    }

    private fun reordenarLista() {
        listaValores.sortWith(compareBy{ it.numero })
    }

    private fun showInputDialog(valor: Valor) {
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle(valor.numero.valor.uppercase())

        val input = EditText(this)
        input.setHint("Introduce la acción")
        input.setText(valor.accion)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            if (input.text.toString().length != 0) {
                listaValores.remove(valor)
                valor.accion = input.text.toString()
                listaValores.add(valor)
                reordenarLista()
            } else {
                val alertaNoHayTexto = android.app.AlertDialog.Builder(this)
                alertaNoHayTexto.setTitle("Error")
                alertaNoHayTexto.setMessage("No has introducido ninguna acción. ¿Qué quieres hacer?")
                alertaNoHayTexto.setPositiveButton("Mantener la acción anterior", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
                alertaNoHayTexto.setNegativeButton("Volver a la acción predeterminada", DialogInterface.OnClickListener { dialog, which ->
                    listaValores.remove(valor)
                    valor.asignarValorPredeterminado()
                    listaValores.add(valor)
                    reordenarLista()
                })
                alertaNoHayTexto.show()
            }
        })
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}
package com.example.k.logic

import java.io.Serializable

class Valor(numero: Numero, accion: String) : Serializable {
    val numero = numero
    var accion = accion

    override fun toString(): String = numero.valor

    fun getStringValor() : String = numero.valor

    fun asignarValorPredeterminado() {
        when (numero) {
            Numero.AS -> accion = "Cascada"
            Numero.DOS-> accion = "Beben los dos de la derecha"
            Numero.TRES -> accion = "Beben los tres de la izquierda"
            Numero.CUATRO -> accion = "Mandas cuatro tragos a una persona"
            Numero.CINCO -> accion = "Repartes cinco tragos"
            Numero.SEIS -> accion = "Comodin"
            Numero.SIETE -> accion = "Te libras"
            Numero.OCHO -> accion = "Bebes durante ocho segundos"
            Numero.NUEVE -> accion = "Beben uno si uno no (Empieza el que ha sacado la carta)"
            Numero.DIEZ -> accion = "Votación"
            Numero.JOTA -> accion = "Beben todos"
            Numero.REINA -> accion = "Beben los hombres"
            Numero.REY -> accion = "Beben las mujeres"
        }
    }
}
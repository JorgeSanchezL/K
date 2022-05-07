package com.example.k.logic

import java.io.Serializable

class Baraja(valores: ArrayList<Valor>) : Serializable {
    val valores = valores

    fun crearBaraja() : ArrayList<Carta> {
        var baraja = arrayListOf<Carta>()
        for (valor: Valor in valores) {
            baraja.add(Carta(valor, Palo.CORAZONES))
            baraja.add(Carta(valor, Palo.DIAMANTES))
            baraja.add(Carta(valor, Palo.PICAS))
            baraja.add(Carta(valor, Palo.TREBOLES))
        }
        return baraja
    }

    fun crearBarajaNormal() : ArrayList<Carta> {
        var valores = ArrayList<Valor>()
        valores.add(Valor(Numero.AS, "Cascada"))
        valores.add(Valor(Numero.DOS, "Beben los dos de la derecha"))
        valores.add(Valor(Numero.TRES, "Beben los tres de la izquierda"))
        valores.add(Valor(Numero.CUATRO, "Mandas cuatro tragos a una persona"))
        valores.add(Valor(Numero.CINCO, "Repartes cinco tragos"))
        valores.add(Valor(Numero.SEIS, "Comodín"))
        //valores.add(Valor(Numero.SIETE, "Te libras"))
        valores.add(Valor(Numero.OCHO, "Bebes durante ocho segundos"))
        valores.add(Valor(Numero.NUEVE, "Beben uno si uno no (Empieza el que ha sacado la carta)"))
        valores.add(Valor(Numero.DIEZ, "Votación"))
        valores.add(Valor(Numero.JOTA, "Beben todos"))
        valores.add(Valor(Numero.REY, "Beben los hombres"))
        valores.add(Valor(Numero.REINA, "Beben las mujeres"))
        return Baraja(valores).crearBaraja()
    }
}
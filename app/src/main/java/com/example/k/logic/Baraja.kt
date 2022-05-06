package com.example.k.logic

class Baraja(valores: ArrayList<Valor>) {
    val valores = valores
    val cartas = crearBaraja()

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
}
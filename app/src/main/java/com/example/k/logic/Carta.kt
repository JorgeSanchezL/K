package com.example.k.logic

class Carta(valor: Valor, palo: Palo) {
    val valor = valor
    val palo = palo
    fun getAccion() : String = valor.accion
    fun getStringImagen() : String = valor.getStringValor() + palo.getStringPalo()
}
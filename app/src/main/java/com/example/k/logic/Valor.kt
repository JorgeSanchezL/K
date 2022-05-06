package com.example.k.logic

class Valor(numero: Numero, accion: String) {
    val numero = numero
    val accion = accion
    fun getStringValor() : String = numero.valor
}
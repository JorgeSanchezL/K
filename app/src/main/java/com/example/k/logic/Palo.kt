package com.example.k.logic

import java.io.Serializable

enum class Palo(val palo: String) {
    CORAZONES("corazones"),
    DIAMANTES("diamantes"),
    PICAS("picas"),
    TREBOLES("treboles");

    fun getStringPalo() : String = palo
}
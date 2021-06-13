package com.reo.trivia.presentation.common

fun getMonthValue(): MutableList<String> {
    val result = mutableListOf<String>()
    for (i in 1..12) {
        result.add(i.toString())
    }
    return result
}

fun getDateValue(): MutableList<String> {
    val result = mutableListOf<String>()
    for (i in 1..31) {
        result.add(i.toString())
    }
    return result
}

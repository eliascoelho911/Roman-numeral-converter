package com.github.eliascoelho911.rnc

import com.github.eliascoelho911.rnc.converter.RomanNumeralToDecimal
import com.github.eliascoelho911.rnc.exception.RomanNumeralInvalid
import java.util.*

fun main() {
    printWelcome()
    val scan = Scanner(System.`in`)
    val converter = RomanNumeralToDecimal()
    loop(scan, converter)
}

private fun loop(scan: Scanner, converter: RomanNumeralToDecimal) {
    while (true) {
        print("| Insert a roman numeral here: ")
        val inputUser = scan.next().trim()
        if (userWantToClose(inputUser)) return
        convertRomanNumeral(converter, inputUser)
        println("|______________________________________________|")
    }
}

private fun convertRomanNumeral(
    converter: RomanNumeralToDecimal,
    romanNumeral: String
) {
    try {
        val result = converter.toConvert(romanNumeral)
        println("| Result: $result")
    } catch (e: RomanNumeralInvalid) {
        println("| Error - ${e.message}")
    }
}

private fun userWantToClose(inputUser: String): Boolean {
    return inputUser == "0"
}

private fun printWelcome() {
    println("|______________________________________________|")
    println("| R O M A N   N U M E R A L  C O N V E R T E R |")
    println("|______________________________________________|")
    println("|____________Insert '0' for to close___________|")
    println("|______________________________________________|")
}
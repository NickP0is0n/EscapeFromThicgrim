package me.nickp0is0n.escapefromthicgrim.utils

interface Logger {
    fun warning(message: String)
    fun error(message: String)
    fun info(message: String)
}
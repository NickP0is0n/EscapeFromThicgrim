package me.nickp0is0n.escapefromthicgrim.utils

class BasicConsoleLogger: Logger {
    override fun warning(message: String) {
        println("[warning] $message")
    }

    override fun error(message: String) {
        println("[error] $message")
    }

    override fun info(message: String) {
        println("[info] $message")
    }
}
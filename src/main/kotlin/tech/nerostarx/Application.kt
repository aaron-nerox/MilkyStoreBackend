package tech.nerostarx

import io.ktor.application.*
import tech.nerostarx.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.home(testing: Boolean = false) {
    configureRouting()
    configureSerialization()
}

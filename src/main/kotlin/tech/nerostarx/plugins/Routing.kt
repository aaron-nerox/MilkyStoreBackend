package tech.nerostarx.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import tech.nerostarx.routes.*

fun Application.configureRouting() {

    routing {
        configureHomeRoute()
        configureCartRouting()
        configureProductCategoryRouting()
        configureProductRouting()
        configureTopPicksRouting()
        configureTransactionRouting()
        configureUserRouting()
    }
}

fun Route.configureHomeRoute(){
    get("/") {
        call.respondText("Welcome home")
    }
}

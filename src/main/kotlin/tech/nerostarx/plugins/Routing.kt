package tech.nerostarx.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import tech.nerostarx.routes.configureCartRouting
import tech.nerostarx.routes.configureProductCategoryRouting
import tech.nerostarx.routes.configureProductRouting
import tech.nerostarx.routes.configureTopPicksRouting

fun Application.configureRouting() {

    routing {
        configureHomeRoute()
        configureCartRouting()
        configureProductCategoryRouting()
        configureProductRouting()
        configureTopPicksRouting()
    }
}

fun Route.configureHomeRoute(){
    get("/") {
        call.respondText("Welcome home")
    }
}

package tech.nerostarx.routes

import io.ktor.routing.*

fun Route.configureTransactionRouting(){
    route("/transaction"){
        // create the transaction
        post("/make") {

        }
    }
}
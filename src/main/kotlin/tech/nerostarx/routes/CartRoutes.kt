package tech.nerostarx.routes

import io.ktor.routing.*

fun Route.configureCartRouting(){
    route("/cart"){
        route("/items"){
            //get the list of cart items
            get{

            }

            //get an item providing it's id
            get("/{id}"){

            }

            // add an item to the cart
            post("/add") {

            }

            // modify an item inside the cart
            put("/update/{id}") {

            }

            // delete an item inside the cart
            delete("/delete/{id}") {

            }

        }
    }
}
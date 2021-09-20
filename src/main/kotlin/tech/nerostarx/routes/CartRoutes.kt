package tech.nerostarx.routes

import io.ktor.routing.*

fun Route.configureCartRouting(){
    route("/cart"){

        //cart's items in general
        route("/items"){
            //get a list of items providing the user's cart reference
            get("/{cart_ref}"){

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

        // a user's specific cart
        route("/users"){
            //get the cart using the user unique ui
            get("/uid"){

            }

            //crate a cart for a specific user
            post("/create"){

            }

            //update the cart by reference
            put("/update/{cart_ref}"){

            }

            //delete the user's cart
            delete("/delete/{cart_ref"){

            }

        }
    }
}
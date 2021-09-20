package tech.nerostarx.routes

import io.ktor.routing.*

fun Route.configureUserRouting(){
    route("/user"){

        //get the user by it's uid
        get("/{uid}"){

        }

        //create a user
        post("/create"){

        }

        //update a user
        put("/update/{uid}"){

        }

        //delete a user
        delete("delete/{uid}"){

        }
    }
}
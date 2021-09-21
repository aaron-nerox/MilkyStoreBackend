package tech.nerostarx.routes

import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import tech.nerostarx.models.User
import tech.nerostarx.models.Users

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

fun toUser(row:ResultRow):User=
    User(
        row[Users.uid],
        row[Users.userName],
        row[Users.userSurname],
        row[Users.userEmail],
        row[Users.userPhone],
        row[Users.userPictureUrl]
    )

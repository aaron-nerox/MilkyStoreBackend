package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import tech.nerostarx.databases.MainDataBase
import tech.nerostarx.models.User
import tech.nerostarx.models.Users

fun Route.configureUserRouting(){
    route("/user"){

        //get the user by it's uid
        get("/{uid}"){

        }

        //create a user
        post("/create"){
            val params = call.receive<User>()
            val result = transaction(MainDataBase.dbInstance){
                Users.insert {
                    it[userName] = params.userName
                    it[userSurname] = params.userSurname
                    it[userEmail] = params.userEmail
                    it[userPhone] = params.userPhone
                    it[userPictureUrl] = params.userPictureUrl
                }
            }.resultedValues

            if(result != null){
                val user = result.map { toUser(it) }
                call.respond(HttpStatusCode.Created, user)
            }else{
                call.respond(HttpStatusCode.InternalServerError, "failed not created")
            }
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

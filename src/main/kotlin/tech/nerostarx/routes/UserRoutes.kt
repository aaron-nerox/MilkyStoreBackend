package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import tech.nerostarx.databases.MainDataBase
import tech.nerostarx.models.User
import tech.nerostarx.models.Users

fun Route.configureUserRouting(){
    route("/user"){

        //get the user by its uid
        get("/{uid}"){
            val uid = call.parameters["uid"] ?: return@get call.respondText("Missing a valid uid")

            val user = transaction(MainDataBase.dbInstance){
                Users.select { Users.uid eq uid.toInt() }.map { toUser(it) }
            }

            call.respond(HttpStatusCode.OK, user)
        }

        //create a user
        post("/create"){
            val params = call.receive<User>()
            val result = transaction(MainDataBase.dbInstance){
                Users.insert {
                    it[userName] = params.userName
                    it[userSurname] = params.userSurname
                    it[userEmail] = params.userEmail!!
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
            val uid = call.parameters["uid"] ?: return@put call.respondText("Missing a valid uid")
            val newUser = call.receive<User>()

            transaction(MainDataBase.dbInstance){
                Users.update({Users.uid eq uid.toInt()}) {
                    it[userName] = newUser.userName
                    it[userSurname] = newUser.userSurname
                    it[userPhone] = newUser.userPhone
                    it[userPictureUrl] = newUser.userPictureUrl
                }
            }

            call.respond(HttpStatusCode.OK, "Fields updated successfully.")
        }

        //delete a user
        delete("delete/{uid}"){
            val uid = call.parameters["uid"] ?: return@delete call.respondText("Missing a valid uid")

            transaction(MainDataBase.dbInstance){
                Users.deleteWhere { Users.uid eq uid.toInt() }
            }

            call.respond(HttpStatusCode.OK, "User deleted successfully.")
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

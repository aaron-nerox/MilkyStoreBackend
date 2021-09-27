package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import tech.nerostarx.databases.MainDataBase
import tech.nerostarx.models.UserTransaction
import tech.nerostarx.models.UserTransactions

fun Route.configureTransactionRouting(){
    route("/transaction"){

        //get a list of transactions by cart reference
        get("/{cartRef}"){
            val cartRef = call.parameters["cartRef"] ?: return@get call.respond(HttpStatusCode.BadRequest,"Missing a valid cart reference.")

            val transactions = transaction(MainDataBase.dbInstance){
                UserTransactions.select {
                    UserTransactions.cartReference eq cartRef.toInt()
                }.map { toTransaction(it) }
            }

            call.respond(status = HttpStatusCode.OK, transactions)
        }
        // create the transaction
        post("/make") {
            val userTransaction = call.receive<UserTransaction>()

            val result = transaction(MainDataBase.dbInstance) {
                UserTransactions.insert {
                    it[cartReference] = userTransaction.cartReference
                }
            }.resultedValues?.map{ toTransaction(it) }

            if(result != null){
                call.respond(status = HttpStatusCode.Created, result)
            }else{
                call.respond(status = HttpStatusCode.InternalServerError, "Error making transaction")
            }
        }
    }
}

fun toTransaction(row: ResultRow): UserTransaction =
    UserTransaction(
        row[UserTransactions.transactionReference],
        row[UserTransactions.cartReference]
    )
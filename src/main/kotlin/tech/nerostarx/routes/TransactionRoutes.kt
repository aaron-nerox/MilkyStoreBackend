package tech.nerostarx.routes

import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import tech.nerostarx.models.UserTransaction
import tech.nerostarx.models.UserTransactions

fun Route.configureTransactionRouting(){
    route("/transaction"){

        //get a list of transactions by cart reference
        get("/cartRef"){

        }
        // create the transaction
        post("/make") {

        }
    }
}

fun toTransaction(row: ResultRow): UserTransaction =
    UserTransaction(
        row[UserTransactions.transactionReference],
        row[UserTransactions.cartReference]
    )
package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

@Serializable
data class UserTransaction(
    var transactionReference: Int,
    var cartReference: Int)

object UserTransactions: Table(){
    val transactionReference: Column<Int> = integer("transaction_reference").autoIncrement()
    val cartReference: Column<Int> = integer("cart_reference")

    override val primaryKey: PrimaryKey = PrimaryKey(transactionReference)
}
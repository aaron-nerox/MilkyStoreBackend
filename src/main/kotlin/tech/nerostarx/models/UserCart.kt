package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

@Serializable
data class UserCart(
    var cartReference: Int,
    var uid: Int,
    var currentTotal: Double)

object UserCarts: Table(){
    val cartReference: Column<Int> = integer("cart_reference").autoIncrement()
    val uid: Column<Int> = integer("uid")
    val currentTotal: Column<Double> = double("current_total")

    override val primaryKey: PrimaryKey = PrimaryKey(cartReference)
}
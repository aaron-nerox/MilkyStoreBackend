package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

@Serializable
data class CartItem (
    var cartItemId: Int,
    var productId: Int,
    var quantity: Double,
    var cartReference: Int)

object CartItems: Table(){
    val cartItemId: Column<Int> = integer("cart_item_id").autoIncrement()
    val productId: Column<Int> = integer("product_id")
    val quantity: Column<Double> = double("quantity")
    val cartReference: Column<Int> = integer("cart_reference")

    override val primaryKey: PrimaryKey = PrimaryKey(cartItemId)
}
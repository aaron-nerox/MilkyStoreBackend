package tech.nerostarx.models

data class CartItem (
    var cartItemId: Int,
    var productId: Int,
    var quantity: Double,
    var cartReference: Int)
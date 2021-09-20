package tech.nerostarx.models

data class Product(
    var productId: Int,
    var productName: String,
    var productUnitPrice: Double,
    var productCategoryId: Int,
    var productImageLink: String,
    var productDescription: String,
    var isProductAvailable: Boolean)

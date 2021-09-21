package tech.nerostarx.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class Product(
    var productId: Int,
    var productName: String,
    var productUnitPrice: Double,
    var productCategoryId: Int,
    var productImageLink: String,
    var productDescription: String,
    var isProductAvailable: Boolean)

object Products: Table(){
    val productId: Column<Int> = integer("product_id").autoIncrement()
    val productName: Column<String> = text("product_name")
    val productUnitPrice: Column<Double> = double("product_unit_price")
    val productCategoryId: Column<Int> = integer("product_category_id")
    val productImageLink: Column<String> = text("product_image_link")
    val productDescription: Column<String> = text("product_description")
    val isProductAvailable: Column<Boolean> = bool("is_product_available")

    override val primaryKey: PrimaryKey = PrimaryKey(productId)
}

package tech.nerostarx.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class ProductCategory(
    var categoryId: Int,
    var categoryImageLink: String,
    var categoryName: String)

object ProductCategories: Table(){
    val categoryId: Column<Int> = integer("category_id").autoIncrement()
    val categoryImageLink: Column<String> = text("category_image_link")
    val categoryName: Column<String> = text("category_name")

    override val primaryKey: PrimaryKey = PrimaryKey(categoryId)

}

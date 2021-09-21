package tech.nerostarx.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class TopPick(
    var topPickId: Int,
    var topPickImageUrl: String,
    var productId: Int)

object TopPicks: Table(){
    val topPickId: Column<Int> = integer("top_pick_id").autoIncrement()
    val topPickImageUrl: Column<String> = text("top_pick_image_url")
    val productId: Column<Int> = integer("product_id")

    override val primaryKey: PrimaryKey = PrimaryKey(topPickId)
}

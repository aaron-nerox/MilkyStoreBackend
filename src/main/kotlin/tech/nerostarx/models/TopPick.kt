package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

@Serializable
data class TopPick(
    var topPickId: Int? = null,
    var topPickImageUrl: String,
    var productId: Int)

object TopPicks: Table(){
    val topPickId: Column<Int> = integer("top_pick_id").autoIncrement()
    val topPickImageUrl: Column<String> = text("top_pick_image_url")
    val productId: Column<Int> = integer("product_id")

    override val primaryKey: PrimaryKey = PrimaryKey(topPickId)
}

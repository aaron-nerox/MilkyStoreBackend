package tech.nerostarx.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class User(
    var uid: Int,
    var userName: String,
    var userSurname: String,
    var userEmail: String,
    var userPhone: String,
    var userPictureUrl: String)

object Users: Table(){
    val uid: Column<Int> = integer("uid").autoIncrement()
    val userName: Column<String> = text("name")
    val userSurname: Column<String> = text("surname")
    val userEmail: Column<String> = text("email")
    val userPhone: Column<String> = text("user_phone")
    val userPictureUrl: Column<String> = text("picture_link")

    override val primaryKey: PrimaryKey = PrimaryKey(uid)

}

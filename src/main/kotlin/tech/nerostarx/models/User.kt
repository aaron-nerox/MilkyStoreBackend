package tech.nerostarx.models

data class User(
    var uid: Int,
    var userName: String,
    var userSurname: String,
    var userEmail: String,
    var userPhone: String,
    var userPictureUrl: String)

package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import tech.nerostarx.databases.MainDataBase
import tech.nerostarx.models.CartItem
import tech.nerostarx.models.CartItems
import tech.nerostarx.models.UserCart
import tech.nerostarx.models.UserCarts

fun Route.configureCartRouting(){
    route("/cart"){

        //cart's items in general
        route("/items"){
            //get a list of items providing the user's cart reference
            get("/{cart_ref}"){

            }

            // add an item to the cart
            post("/add") {

            }

            // modify an item inside the cart
            put("/update/{id}") {

            }

            // delete an item inside the cart
            delete("/delete/{id}") {

            }

        }

        // a user's specific cart
        route("/users"){
            //get the cart using the user unique ui
            get("/{uid}"){
                val uid = call.parameters["uid"] ?: return@get call.respond(HttpStatusCode.BadRequest,"Missing a valid uid")

                val result = transaction(MainDataBase.dbInstance){
                    UserCarts.select { UserCarts.uid eq uid.toInt() }.map { toUserCart(it) }
                }

                call.respond(status = HttpStatusCode.OK, result)
            }

            //crate a cart for a specific user
            post("/create"){
                val userCart = call.receive<UserCart>()

                val result = transaction(MainDataBase.dbInstance) {
                    UserCarts.insert {
                        it[uid] = userCart.uid!!
                        it[currentTotal] = userCart.currentTotal
                    }
                }.resultedValues?.map { toUserCart(it) }

                if(result != null){
                    call.respond(status = HttpStatusCode.Created, result)
                }else{
                    call.respond(status = HttpStatusCode.InternalServerError, "Error creating the cart")
                }

            }

            //update the cart by reference
            put("/update/{cart_ref}"){
                val cartRef = call.parameters["cart_ref"]

                val newCart = call.receive<UserCart>()

                if(cartRef != null){
                    transaction(MainDataBase.dbInstance){
                        UserCarts.update({ UserCarts.cartReference eq cartRef.toInt()}) {
                            it[currentTotal] = newCart.currentTotal
                        }
                    }
                }else{
                    return@put call.respond(HttpStatusCode.BadRequest,"Missing a valid cart reference")
                }

                call.respond(HttpStatusCode.OK, "User cart updated.")
            }

            //delete the user's cart
            delete("/delete/{cart_ref}"){
                val cartRef = call.parameters["cart_ref"] ?: return@delete call.respond(HttpStatusCode.BadRequest,"Missing a valid cart reference")

                transaction(MainDataBase.dbInstance){
                    UserCarts.deleteWhere { UserCarts.cartReference eq cartRef.toInt() }
                }

                call.respond(HttpStatusCode.OK, "User cart deleted.")
            }

        }
    }
}

fun toUserCart(row: ResultRow):UserCart =
    UserCart(
        row[UserCarts.cartReference],
        row[UserCarts.uid],
        row[UserCarts.currentTotal]
    )

fun toCartItem(row: ResultRow):CartItem =
    CartItem(
        row[CartItems.cartItemId],
        row[CartItems.productId],
        row[CartItems.quantity],
        row[CartItems.cartReference]
    )
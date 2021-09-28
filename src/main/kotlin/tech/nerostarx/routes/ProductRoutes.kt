package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import tech.nerostarx.databases.MainDataBase
import tech.nerostarx.models.*


fun Route.configureProductRouting(){
    route("/products"){
        //get the list of products
        get {
            val productList = transaction(MainDataBase.dbInstance){
                Products.selectAll().map { toProduct(it) }
            }

            call.respond(HttpStatusCode.OK, productList)
        }

        //get a product by id
        get("/{id}"){
            val id = call.parameters["id"] ?: return@get call.respond("invalid id")

            val result = transaction(MainDataBase.dbInstance){
                Products.select { Products.productId eq id.toInt() }.map { toProduct(it) }
            }

            call.respond(HttpStatusCode.OK, result)
        }

        //get a product by category id
        get("/category/{categoryId}"){
            val id = call.parameters["categoryId"] ?: return@get call.respond("invalid category id")

            val result = transaction(MainDataBase.dbInstance){
                Products.select { Products.productCategoryId eq id.toInt() }.map { toProduct(it) }
            }

            call.respond(HttpStatusCode.OK, result)
        }

        // add a product to the list
        post("/add"){
            val newProduct = call.receive<Product>()

            val result = transaction(MainDataBase.dbInstance){
                Products.insert{
                    it[productName] = newProduct.productName
                    it[productDescription] = newProduct.productDescription
                    it[productCategoryId] = newProduct.productCategoryId
                    it[productUnitPrice] = newProduct.productUnitPrice
                    it[productImageLink] = newProduct.productImageLink
                    it[isProductAvailable] = newProduct.isProductAvailable
                }
            }.resultedValues?.map { toProduct(it) }

            if(result != null){
                call.respond(HttpStatusCode.Created, result)
            }else{
                call.respond(HttpStatusCode.InternalServerError, "error during creation.")
            }
        }

        //modify a product from the list
        put("/update/{id}") {
            val id = call.parameters["id"]
            val newProduct = call.receive<Product>()

            if(id != null){
                transaction(MainDataBase.dbInstance){
                    Products.update({Products.productId eq id.toInt()}){
                        it[productName] = newProduct.productName
                        it[productDescription] = newProduct.productDescription
                        it[productCategoryId] = newProduct.productCategoryId
                        it[productUnitPrice] = newProduct.productUnitPrice
                        it[productImageLink] = newProduct.productImageLink
                        it[isProductAvailable] = newProduct.isProductAvailable
                    }
                }

                call.respond(HttpStatusCode.OK, "updated.")
            }else{
                return@put call.respond("invalid id")
            }
        }

        // delete a product from the list
        delete("/delete/{id}"){
            val id = call.parameters["id"] ?: return@delete call.respond("invalid id")

            transaction(MainDataBase.dbInstance){
                Products.deleteWhere { Products.productId eq id.toInt() }
            }

            call.respond(HttpStatusCode.OK, "deleted.")

        }
    }
}

fun Route.configureProductCategoryRouting(){
    route("/categories"){
        //get all the categories
        get{
            val productCategories = transaction(MainDataBase.dbInstance){
                ProductCategories.selectAll().map { toProductCategory(it) }
            }

            call.respond(HttpStatusCode.OK, productCategories)
        }

        //add a category
        post("/add"){
            val newCategory = call.receive<ProductCategory>()

            val result = transaction(MainDataBase.dbInstance){
                ProductCategories.insert{
                    it[categoryName] = newCategory.categoryName
                    it[categoryImageLink] = newCategory.categoryImageLink
                }
            }.resultedValues?.map { toProductCategory(it) }

            if(result != null){
                call.respond(HttpStatusCode.Created, result)
            }else{
                call.respond(HttpStatusCode.InternalServerError, "error during creation.")
            }
        }

        //update a category
        put("/update/{id}"){
            val id = call.parameters["id"]
            val newCategory = call.receive<ProductCategory>()

            if(id != null){
                transaction(MainDataBase.dbInstance){
                    ProductCategories.update({ProductCategories.categoryId eq id.toInt()}){
                        it[categoryName] = newCategory.categoryName
                        it[categoryImageLink] = newCategory.categoryImageLink
                    }
                }

                call.respond(HttpStatusCode.OK, "updated.")
            }else{
                return@put call.respond("invalid id")
            }
        }

        //delete a category
        delete("/delete/{id}"){
            val id = call.parameters["id"] ?: return@delete call.respond("invalid id")

            transaction(MainDataBase.dbInstance){
                ProductCategories.deleteWhere { ProductCategories.categoryId eq id.toInt() }
            }

            call.respond(HttpStatusCode.OK, "deleted.")
        }
    }
}

fun Route.configureTopPicksRouting(){
    route("/top_picks"){
        //get the list of top picks
        get{
            val topPicks = transaction(MainDataBase.dbInstance){
                TopPicks.selectAll().map { toTopPick(it) }
            }

            call.respond(HttpStatusCode.OK, topPicks)
        }

        // add a top pick
        post("/add"){
            val newPick = call.receive<TopPick>()
            val result = transaction(MainDataBase.dbInstance){
                TopPicks.insert{
                    it[topPickImageUrl] = newPick.topPickImageUrl
                    it[productId] = newPick.productId
                }
            }.resultedValues?.map { toTopPick(it) }

            if(result != null){
                call.respond(HttpStatusCode.Created, result)
            }else{
                call.respond(HttpStatusCode.InternalServerError, "error during creation.")
            }
        }

        // update a top pick
        put("/update/{id}"){
            val id = call.parameters["id"]
            val newPick = call.receive<TopPick>()

            if(id != null){
                transaction(MainDataBase.dbInstance){
                    TopPicks.update({TopPicks.topPickId eq id.toInt()}){
                        it[topPickImageUrl] = newPick.topPickImageUrl
                    }
                }

                call.respond(HttpStatusCode.OK, "updated.")
            }else{
                return@put call.respond("invalid id")
            }
        }

        // delete a top pick
        delete("/delete/{id}"){
            val id = call.parameters["id"] ?: return@delete call.respond("invalid id")
            transaction(MainDataBase.dbInstance){
                TopPicks.deleteWhere { TopPicks.topPickId eq id.toInt() }
            }

            call.respond(HttpStatusCode.OK, "deleted.")
        }
    }
}

fun toProduct(row:ResultRow):Product=
    Product(
        row[Products.productId],
        row[Products.productName],
        row[Products.productUnitPrice],
        row[Products.productCategoryId],
        row[Products.productImageLink],
        row[Products.productDescription],
        row[Products.isProductAvailable]
    )

fun toProductCategory(row: ResultRow):ProductCategory =
    ProductCategory(
        row[ProductCategories.categoryId],
        row[ProductCategories.categoryImageLink],
        row[ProductCategories.categoryName]
    )

fun toTopPick(row: ResultRow):TopPick=
    TopPick(
        row[TopPicks.topPickId],
        row[TopPicks.topPickImageUrl],
        row[TopPicks.productId]
    )
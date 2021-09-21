package tech.nerostarx.routes

import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import tech.nerostarx.models.*


fun Route.configureProductRouting(){
    route("/products"){
        //get the list of products
        get {

        }

        //get a product by id
        get("/{id}"){

        }

        //get a product by category id
        get("/category/{categoryId}"){

        }

        // add a product to the list
        post("/add"){

        }

        //modify a product from the list
        put("/update/{id}") {

        }

        // delete a product from the list
        delete("/delete/{id}"){

        }
    }
}

fun Route.configureProductCategoryRouting(){
    route("/categories"){
        //get all the categories
        get{

        }

        //add a category
        post("/add"){

        }

        //update a category
        put("/update"){

        }

        //delete a category
        delete("/delete/{id}"){

        }
    }
}

fun Route.configureTopPicksRouting(){
    route("/top_picks"){
        //get the list of top picks
        get{

        }

        //get a product from a top pick by it's id
        get("/{id_product}"){

        }

        // add a top pick
        post("/add"){

        }

        // update a top pick
        put("/update/{id}"){

        }

        // delete a top pick
        delete("/delete/{id}"){

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
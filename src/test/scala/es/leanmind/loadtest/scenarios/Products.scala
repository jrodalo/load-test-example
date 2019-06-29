package es.leanmind.loadtest.scenarios

import io.gatling.core.Predef._
import io.gatling.core.feeder._
import io.gatling.http.Predef._

object Products {

    private val products = jsonFile("products.json").circular

    val search = feed(products)
        .exec(
            http("Search")
                .get("/products?query=${productName}")
        )

    val buy = feed(products)
        .exec(
            http("Select")
                .get("/products/${productId}")
        )
        .pause(5)
        .exec(
            http("Buy")
                .post("/products/${productId}")
                .formParam("action", "buy")
        )
}

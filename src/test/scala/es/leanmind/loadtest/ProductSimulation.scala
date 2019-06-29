package es.leanmind.loadtest

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import es.leanmind.loadtest.scenarios._

class ProductSimulation extends Simulation {

    val httpProtocol = http.baseUrl(Config.SERVER)

    val searchProducts = scenario("Search Products").exec(Products.search)
    val buyProducts = scenario("Buy Products").exec(Products.buy)

    setUp(
        searchProducts.inject(rampUsers(10).during(10)),
        buyProducts.inject(atOnceUsers(5))
    ).protocols(httpProtocol)
}

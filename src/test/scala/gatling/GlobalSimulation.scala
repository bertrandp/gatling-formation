package gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

/**
  * Created by Bertrand Pestre on 30/03/17.
  */
class GlobalSimulation extends Simulation{


  val httpProtocol = http
    .baseURL("http://computer-database.gatling.io")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0")

  val scnUSer = scenario("User")
    .exec(Browse.browse, Search.search)

  val scnAdmin = scenario("Admin")
    .exec(Browse.browse, Search.search, Edit.edit)

  setUp(scnUSer.inject(rampUsers(10)over(10)), scnAdmin.inject(rampUsers(2)over(10)))
    .protocols(httpProtocol)
    .assertions(global.failedRequests.count.is(0))
    .assertions(global.responseTime.percentile1.lt(20))

}

package gatling

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

object Search {
  val search = feed(csv("search.csv").circular)
    .repeat(2) {exec(http("Page computer")
    .get("/computers"))
    .pause(1)
    .exec(http("Search ${searchCriterion}")
      .get("/computers?f=${searchCriterion}")
      .check(css("a:contains('${searchComputerName}')", "href").saveAs("url")))
    .pause(1)
    .exec(http("Select")
      .get("${url}"))}
}


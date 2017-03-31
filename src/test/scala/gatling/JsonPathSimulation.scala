package gatling

import io.gatling.core.Predef._
import io.gatling.core.session.Expression
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

import scala.concurrent.duration._


/**
  * Created by Bertrand Pestre on 30/03/17.
  */
class JsonPathSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://jsonplaceholder.typicode.com")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0")

  val patchTemplate: Expression[String] = session => {
    for {
        i <- session("name").validate[String]
      } yield s"""{"name": "${i.reverse}"}"""
  }


  val scn = scenario("JSONPath")
    .exec(http("Comments")
      .get("/comments/1")
      .check(jsonPath("$.name").saveAs("name")))
//
//    .exec {
//      session =>
//        println(session)
//        println(session("name").as[String])
//
//        val session2 = session.set("reverseName",
//          for {
//            i <- session("name").validate[String]
//          } yield i.reverse
//        )
//
//        println(session2("reverseName").as[String])
//        session2
//    }

    .exec (http("Patch")
        .patch("/comments/1")
        .header("Content-Type", "application/json")
        .body(StringBody(patchTemplate)))

//        .body(StringBody("""{"name": "${reverseName}"}""")))

//    .exec(http("Comments")
//      .get("/comments/1")
//      .check(jsonPath("$.name").saveAs("fooName")))


//    .exec(http("Comments")
//      .get("/comments")
//    .check(jsonPath("""$[?(@.email == "Eliseo@gardner.biz")].postId""").ofType[Int].is(1)))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}

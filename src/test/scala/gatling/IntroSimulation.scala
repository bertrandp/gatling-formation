package gatling


import io.gatling.commons.validation.Validation
import io.gatling.core.Predef.{feed, _}
import io.gatling.core.session.SessionAttribute
import io.gatling.http.Predef._

import scala.concurrent.duration._

/**
  * Created by Bertrand Pestre on 31/03/17.
  */
class IntroSimulation extends Simulation{

//  val httpConf = http
//    .baseURL("http://computer-database.gatling.io") // Here is the root for all relative URLs
//    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
//    .acceptEncodingHeader("gzip, deflate")
//    .acceptLanguageHeader("en-US,en;q=0.5")
//    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("Scenario Name")
    .feed(Iterator.from(1)
      .map{i: Int =>
        Map("email" -> s"user$i@gatling.io")
      }
    )
    .exec {
      session =>
        println(session)
        println(session("email").as[String])
        session
    }

//    .exec { session =>
//    println(session)
//    val session2 = session.setAll("1" -> 1 , "2" -> 2)
//    println(session2)
//    session2 }
//
//    .exec { session =>
//      println(session("1").as[Int] + session("2").as[Int])
//      session }
//
//    .exec { session =>
//      val result: Validation[Session] =
//        for {
//          i <- session("1").validate[Int]
//          j <- session("2").validate[Int]
//       } yield session.set("new", i + j)
//
//      result}
//
//    .exec { session =>
//      println(session("new").as[Int])
//      session }




  setUp(scn.inject(atOnceUsers(10)))

}

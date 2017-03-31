package gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object Browse {
	val browse = 	repeat(2, "count") {
		exec(http("Page ${count}")
			.get("/computers?p=${count}"))
	}
}

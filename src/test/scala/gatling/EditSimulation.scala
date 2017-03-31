package gatling

import java.util.concurrent.ThreadLocalRandom

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

object Edit {
	val edit = tryMax(3) {
		exec(http("Form")
			.get("/computers/new"))
			.pause(1)
			.exec(http("Post")
				.post("/computers")
				.formParam("name", "SuperMario")
				.formParam("introduced", "2000-12-12")
				.formParam("discontinued", "2001-01-01")
				.formParam("company", "3"))
	}
}

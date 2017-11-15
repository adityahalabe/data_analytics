package controllers

import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._

class HomeControllerSpec extends PlaySpec with OneAppPerTest {

  "HomeController" should {

    "render the index page with reports section" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("report")
    }

    "render the index page with query section" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Get Results")
    }

  }

}

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class IntegrationSpec extends PlaySpec with OneServerPerTest with OneBrowserPerTest with HtmlUnitFactory {

  "Application" should {

    "Open Home page with heading" in {

      go to ("http://localhost:" + port)
      pageSource must include ("Data Analysis")
    }

    "get query results" in {
      go to ("http://localhost:" + port + "/query?countryName=nether&countryCode=")
      pageSource must include ("Netherlands")
    }

    "get country wise airport report" in {
      go to ("http://localhost:" + port + "/report/airport-count")
      pageSource must include ("United States")
    }

    "get country wise surface report" in {
      go to ("http://localhost:" + port + "/report/surface-count")
      pageSource must include ("United States")
    }

    "get runway identification report" in {
      go to ("http://localhost:" + port + "/report/runway-ident")
      pageSource must include ("H1")
    }


  }
}

package controllers

import model._
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._
import services.{ReportService, QueryService}

class ReportControllerSpec extends PlaySpec with OneAppPerTest with MockitoSugar {

  "Report Controller" should {

    "get country with airport count report" in {
      val mockReportService = mock[ReportService]

      val fakeRequest = FakeRequest(GET, "/report/airport-count")
      val reportController = new ReportController(mockReportService)

      when(mockReportService.getCountryWithAirportReport) thenReturn (CountryWithAirportReport(List(CountryWithAirport("United States",25342)),List(CountryWithAirport("Brazil",3464))))

      val actualResponse = reportController.getCountryWithAirportReport().apply(fakeRequest)

      status(actualResponse) mustEqual 200
      verify(mockReportService,times(1)).getCountryWithAirportReport
      contentAsString(actualResponse).contains("United States") mustBe true

    }

    "get country with surface count report" in {
      val mockReportService = mock[ReportService]

      val fakeRequest = FakeRequest(GET, "/report/surface-count")
      val reportController = new ReportController(mockReportService)

      when(mockReportService.getCountryWithSurfaceReport()) thenReturn (CountryWithSurfaceReport(List(CountryWithSurface("United States",25342))))

      val actualResponse = reportController.getCountryWithSurfaceReport().apply(fakeRequest)

      status(actualResponse) mustEqual 200
      verify(mockReportService,times(1)).getCountryWithSurfaceReport()
      contentAsString(actualResponse).contains("United States") mustBe true

    }

    "get runway identification report" in {
      val mockReportService = mock[ReportService]

      val fakeRequest = FakeRequest(GET, "/report/runway-ident")
      val reportController = new ReportController(mockReportService)

      when(mockReportService.getTopRunwayIdentReport()) thenReturn (RunwayWithCountReport(List(RunwayWithCount("H1",25342))))

      val actualResponse = reportController.getTopRunwayIdentReport().apply(fakeRequest)

      status(actualResponse) mustEqual 200
      verify(mockReportService,times(1)).getTopRunwayIdentReport()
      contentAsString(actualResponse).contains("H1") mustBe true

    }
  }

}

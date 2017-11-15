package controllers

import model.QueryResult
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._
import services.QueryService

class QueryControllerSpec extends PlaySpec with OneAppPerTest with MockitoSugar {

  "Query Controller" should {

    "get query results when both country code or country name are present" in {
      val mockQueryService = mock[QueryService]

      val fakeRequest = FakeRequest(GET, "/query?countryName=Netherlands&countryCode=EU")
      val queryController = new QueryController(mockQueryService)

      when(mockQueryService.getQueryResults("netherlands","eu")) thenReturn List(QueryResult("Netherlands","Wieringermeer Glider Field","grass","3149","134"))

      val actualResponse = queryController.getQueryResults.apply(fakeRequest)

      status(actualResponse) mustEqual 200
      verify(mockQueryService,times(1)).getQueryResults("netherlands","eu")
      contentAsString(actualResponse).contains("Netherlands") mustBe true

    }

    "get query results when either country code or country name is present" in {
      val mockQueryService = mock[QueryService]

      val fakeRequest = FakeRequest(GET, "/query?countryName=Netherlands&countryCode=")
      val queryController = new QueryController(mockQueryService)

      when(mockQueryService.getQueryResults("netherlands","")) thenReturn List(QueryResult("Netherlands","Wieringermeer Glider Field","grass","3149","134"))

      val actualResponse = queryController.getQueryResults.apply(fakeRequest)

      status(actualResponse) mustEqual 200
      verify(mockQueryService,times(1)).getQueryResults("netherlands","")
      contentAsString(actualResponse).contains("Netherlands") mustBe true
    }
  }

}

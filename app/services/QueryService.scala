package services

import model.QueryResult
import scala.collection.JavaConverters._
import org.apache.spark.sql.functions._

/**
  * Created by ahm2320 on 11/11/17.
  */
class QueryService extends CountriesDataFrame with AirportsDataFrame with RunwaysDataFrame{

  def getQueryResults(countryName : String,countryCode : String) : List[QueryResult] = {

    val countryDf = getCountryDataFrame()
                      .filter(getQueryString(countryName,countryCode))

    val airportDf = getAirportDataFrame()
    val runwayDf = getRunwayDataFrame()
    val airportCountryJoin = countryDf.join(airportDf, countryDf("code") === airportDf("iso_country"))

    val runwayJoin = airportCountryJoin.join(runwayDf, runwayDf("airport_ident") === airportCountryJoin("ident"))
                                    .select("country_name","airport_name","length_ft","width_ft","surface")
                                    .collectAsList()
                                    .asScala

    new ReportService().getCountryWithAirportReport()
    runwayJoin.map(row => QueryResult(row.getAs[String]("country_name"),
                            row.getAs[String]("airport_name"),
                            row.getAs[String]("length_ft"),
                            row.getAs[String]("width_ft"),
                            row.getAs[String]("surface"))).toList

    }

  private def getQueryString(countryName : String,countryCode : String) : String = {
    (countryName,countryCode) match {
      case (countryName,"") => s"lower(name) like '%$countryName%'"
      case ("",countryCode) => s"lower(code) like '$countryCode'"
      case (countryName,countryCode) => s"lower(name) like '%$countryName%' AND lower(code) like '$countryCode'"
      case _ => s"lower(name) like '%%'"
    }
  }
}

package services

import model.QueryResult
import scala.collection.JavaConverters._

/**
  * Created by ahm2320 on 11/11/17.
  */
class QueryService extends CountriesDataFrame with AirportsDataFrame with RunwaysDataFrame{

   def getQueryResults(countryName : String) : List[QueryResult] = {

     val countryDf = getCountryDataFrame()
                          .filter(s"name like '%$countryName%' OR code like '%$countryName%'")

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
 }

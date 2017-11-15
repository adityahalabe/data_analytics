package services

import com.google.inject.Inject
import datahandlers.DataFrameHandler
import model.QueryResult
import scala.collection.JavaConverters._
import org.apache.spark.sql.functions._

/**
  * Created by ahm2320 on 11/11/17.
  */
class QueryService @Inject() (dataFrameHandler: DataFrameHandler){

  def getQueryResults(countryName : String,countryCode : String) : List[QueryResult] = {

    val runwayJoin = dataFrameHandler.getAllJoin().filter(getQueryString(countryName,countryCode))
                                    .select("country_name","airport_name","length_ft","width_ft","surface")
                                    .collectAsList()
                                    .asScala

    runwayJoin.map(row => QueryResult(row.getAs[String]("country_name"),
                            row.getAs[String]("airport_name"),
                            row.getAs[String]("length_ft"),
                            row.getAs[String]("width_ft"),
                            row.getAs[String]("surface"))).toList

    }

  private def getQueryString(countryName : String,countryCode : String) : String = {
    (countryName,countryCode) match {
      case (countryName,"") => s"lower(country_name) like '%$countryName%'"
      case ("",countryCode) => s"lower(code) like '$countryCode'"
      case (countryName,countryCode) => s"lower(country_name) like '%$countryName%' AND lower(code) like '$countryCode'"
      case _ => s"lower(country_name) like '%%'"
    }
  }
}

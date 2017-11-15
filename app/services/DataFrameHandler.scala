package services

import com.google.inject.Inject
import model.ApplicationConfig
import org.apache.spark.sql.DataFrame

/**
 * Created by ahm2320 on 11/14/17.
 */
class DataFrameHandler @Inject() (applicationConfig: ApplicationConfig) extends CountriesDataFrame
                                                                        with AirportsDataFrame
                                                                        with RunwaysDataFrame{

  def getAirportWithCountryJoin() ={
    val countryDf = getCountryDataFrame(applicationConfig.getCuntriesDataSource)
    val airportDf = getAirportDataFrame(applicationConfig.getAirportsDataSource)
    countryDf.join(airportDf, countryDf("code") === airportDf("iso_country"))
  }

  def getAllJoin() ={
    val countryDf = getCountryDataFrame(applicationConfig.getCuntriesDataSource)
    val airportDf = getAirportDataFrame(applicationConfig.getAirportsDataSource)
    val runwayDf = getRunwayDataFrame(applicationConfig.getRunwaysDataSource)
    val airportCountryJoin = countryDf.join(airportDf, countryDf("code") === airportDf("iso_country"))
    airportCountryJoin.join(runwayDf, runwayDf("airport_ident") === airportCountryJoin("ident"))
  }

   def getRunwayDataFrame() : DataFrame = getRunwayDataFrame(applicationConfig.getRunwaysDataSource)
}

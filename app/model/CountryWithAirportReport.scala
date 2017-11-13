package model

/**
 * Created by ahm2320 on 11/12/17.
 */
case class CountryWithAirportReport(maxAirports : List[CountryWithAirport], minAirports : List[CountryWithAirport])

case class CountryWithAirport(countryName : String, airportCount : Long)

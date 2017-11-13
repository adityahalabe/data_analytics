package model

/**
 * Created by ahm2320 on 11/12/17.
 */
case class CountryWithSurfaceReport(maxSurface : List[CountryWithSurface])

case class CountryWithSurface(countryName : String, surfaceCount : Long)

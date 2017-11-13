package controllers

import javax.inject.Singleton

import com.google.inject.Inject
import play.api.mvc.{Controller, _}
import services.{ReportService, QueryService}

/**
  * Created by ahm2320 on 11/11/17.
  */
@Singleton
class ReportController @Inject() (reportService: ReportService) extends Controller {

   def getCountryWithAirportReport() = Action {
     request =>{
       val report = reportService.getCountryWithAirportReport()
       Ok(views.html.countryWithAirportReport(report))
     }
   }

  def getCountryWithSurfaceReport() = Action {
    request =>{
      val report = reportService.getCountryWithSurfaceReport()
      Ok(views.html.countryWithSurfaceReport(report))
    }
  }

  def getTopRunwayIdentReport() = Action {
    request =>{
      val report = reportService.getTopRunwayIdentReport()
      Ok(views.html.runwayIdentReport(report))
    }
  }
 }

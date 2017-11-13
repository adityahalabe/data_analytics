package controllers

import javax.inject.Singleton

import com.google.inject.Inject
import play.api.mvc.{Controller, _}
import services.QueryService

/**
  * Created by ahm2320 on 11/11/17.
  */
@Singleton
class QueryController @Inject() (queryService: QueryService) extends Controller {

   def getQueryResults() = Action {
     request =>{
       val countryName = request.getQueryString("countryName").getOrElse("")
       println(countryName)
       val list = queryService.getQueryResults(countryName)
       Ok(views.html.queryResults(list))
     }
   }
 }

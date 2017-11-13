package controllers

import javax.inject.Singleton

import com.google.inject.Inject
import play.api.mvc.{Controller, _}
import services.QueryService

/**
  * Created by ahm2320 on 11/11/17.
  */
@Singleton
class HomeController @Inject() (queryService: QueryService) extends Controller {

   def home() = Action {
     request =>{

       Ok(views.html.index())
     }
   }
 }

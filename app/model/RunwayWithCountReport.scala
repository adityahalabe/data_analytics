package model

/**
 * Created by ahm2320 on 11/12/17.
 */
case class RunwayWithCountReport(runways : List[RunwayWithCount])

case class RunwayWithCount(runwayIdent : String, totalCount : Long)

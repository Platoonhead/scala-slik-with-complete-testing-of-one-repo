package connections

import slick.jdbc.MySQLProfile

trait ConnectedDbMysql extends DBProvider{

  val driver = MySQLProfile

  import driver.api._

  val db = Database.forConfig("db")

}
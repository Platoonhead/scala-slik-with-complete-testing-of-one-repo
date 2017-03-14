package connections

import slick.jdbc.PostgresProfile

trait ConnectedDbPostgres extends DBProvider {

  val driver = PostgresProfile

  import driver.api._

  val db = Database.forConfig("myPostgresDB")

}

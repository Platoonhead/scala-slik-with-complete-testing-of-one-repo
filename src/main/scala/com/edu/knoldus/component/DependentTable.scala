package com.edu.knoldus.component

import com.edu.knoldus.models.Dependents
import connections.{ConnectedDbMysql, DBProvider}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait DependentTable{

  this: DBProvider =>

  import driver.api._

  class DependentTable(tag: Tag) extends Table[Dependents](tag, "dependents") {

    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age")
    val reference= column[Int]("employeeReference")
    def * = (reference,name,relation,age,id) <> (Dependents.tupled, Dependents.unapply)
    def depen = foreignKey("reference_fk",reference,ForEmployeeTable.employeeTableQuery)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete =ForeignKeyAction.Cascade)

  }

  val dependentTableQuery = TableQuery[DependentTable]

}
  trait DependentComponent extends DependentTable with ProjectTable{

    this :  DBProvider =>
    import driver.api._

    def createProjectTable: Future[Unit] = db.run(dependentTableQuery.schema.create)

    def insertRecord(dependent: Dependents): Future[Int] = db.run {
      dependentTableQuery.returning(dependentTableQuery.map(_.id)) += dependent
    }

    def deleteRecordWithId(id: Int): Future[Int] = db.run {
      dependentTableQuery.filter(_.id === id).delete
    }

    def updateRecord(dependent: Dependents): Future[Int] =db.run {
      dependentTableQuery.filter(_.id === dependent.id).update(dependent)
    }

    def getAllRecords: Future[List[Dependents]] = db.run {
      dependentTableQuery.to[List].result
    }

    def upsertRecord(dependent: Dependents): Future[Boolean] ={
      updateRecord(dependent).map{
      res => if(res == 0) { insertRecord(dependent) ; true} else true
      }.recover {
      case ex: Throwable => false
      }
    }

    def getAllSeniorCitizenDependents: Future[List[Dependents]] = db.run {
      dependentTableQuery.to[List].filter(_.age>=60).result
    }

  }

  object ForDependentTable extends DependentComponent with ConnectedDbMysql







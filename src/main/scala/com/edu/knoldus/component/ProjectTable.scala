package com.edu.knoldus.component
import com.edu.knoldus.models.Project
import connections.{ConnectedDbMysql, DBProvider}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ProjectTable extends ConnectedDbMysql{

  this : DBProvider =>

  import driver.api._

  class ProjectTable(tag: Tag) extends Table[Project](tag, "project") {

    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val projectname = column[String]("projectName")
    val teamcount = column[Int]("teamCount")
    val leadid= column[Int]("leadsID")
    def * = (projectname,teamcount,leadid,id) <> (Project.tupled, Project.unapply)
    def proj = foreignKey("lead_fk",leadid,ForEmployeeTable.employeeTableQuery)(_.id, onUpdate=ForeignKeyAction.Restrict)

  }

  val projectTableQuery = TableQuery[ProjectTable]

}
  trait ProjectComponent extends ProjectTable {

    this : DBProvider =>
    import driver.api._

    def createProjectTable: Future[Unit] = db.run(projectTableQuery.schema.create)

    def insertRecord(proj: Project): Future[Int] = db.run {
      projectTableQuery.returning(projectTableQuery.map(_.id)) += proj
    }

    def deleteRecordWithId(id: Int): Future[Int] = db.run {
      projectTableQuery.filter(_.id === id).delete
    }

    def updateRecord(proj: Project): Future[Int] =db.run {
      projectTableQuery.filter(_.id === proj.id).update(proj)
    }

    def getAllRecords: Future[List[Project]] = db.run {
      projectTableQuery.to[List].result
    }

    def upsertRecord(proj: Project): Future[Boolean] ={
      updateRecord(proj).map{
      res => if(res == 0) { insertRecord(proj) ; true} else false
      }.recover {
      case ex: Throwable => false
      }
    }

    def getAllBigProjects: Future[List[Project]] = db.run {
      projectTableQuery.to[List].filter(_.teamcount>5).result
    }

  }

  object ForProjectTable extends ProjectComponent





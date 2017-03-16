package com.edu.knoldus.component
import com.edu.knoldus.models.Project
import connections.{ConnectedDbMysql, DBProvider}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ProjectTable{

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
  trait ProjectComponent extends ProjectTable with EmployeeTable{

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
      res => if(res == 0) { insertRecord(proj) ; true} else true
      }.recover {
      case ex: Throwable => false
      }
    }

    def getAllBigProjects: Future[List[Project]] = db.run {
      projectTableQuery.to[List].filter(_.teamcount>5).result
    }

    def crossJoin: Future[List[(Int, String)]] = db.run{
      (for {
        (e, p) <-  employeeTableQuery join projectTableQuery
      } yield (e.id, p.projectname)).to[List].result
    }

    def zipJoinQuery: Future[List[(String, String)]] =db.run {
      (for {
        (c, s) <- employeeTableQuery zip projectTableQuery
      } yield (c.name, s.projectname)).to[List].result
    }

    def teamCount: Future[Option[Int]] = db.run{

      val a = projectTableQuery.map(_.teamcount)
      a.max.result
    }

    def defaultProjectName(proj: Project) = db.run{
      val insert = projectTableQuery.returning(projectTableQuery.map(_.id)) += proj
      val newProject = proj.copy(projectName = "default project")
      val update = projectTableQuery.filter(_.id === proj.id).update(newProject)
      insert andThen update
   }

    def transaction(proj: Project) = db.run{

      val entry2 = proj.copy(projectName = "first")
      val entry3 = proj.copy(projectName = "second")
      val insert1 = projectTableQuery.returning(projectTableQuery.map(_.id)) += entry2
      val insert2 = projectTableQuery.returning(projectTableQuery.map(_.id)) += entry3
      (insert1 andThen(insert2)).transactionally

    }

    def insertPlainSql = {
      val action = sqlu"insert into project values('changedName',10,5,100);"
      db.run(action)
    }


    def insertRecordObj(proj: Project): Future[Project] = {
      val insertQuery = projectTableQuery returning projectTableQuery.map(_.id) into ((item, id) => item.copy(id = id))
      val action = insertQuery += proj
      db.run(action)
    }



  }

  object ForProjectTable extends ProjectComponent with ConnectedDbMysql





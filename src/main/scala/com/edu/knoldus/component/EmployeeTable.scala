package com.edu.knoldus.component
import com.edu.knoldus.models.Employee
import connections.{ConnectedDbMysql, DBProvider}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait EmployeeTable extends ConnectedDbMysql {

    this : DBProvider =>
    import driver.api._

    class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee"){

      val id = column[Int]("id", O.PrimaryKey,O.AutoInc)
      val name = column[String]("name")
      val experience = column[Double]("experience")
      def * = (name,experience,id) <> (Employee.tupled, Employee.unapply)
    }
    val employeeTableQuery = TableQuery[EmployeeTable]

  }

trait EmployeeComponent extends EmployeeTable {

    this : DBProvider =>
    import driver.api._

    def createEmployeeTable: Future[Unit] = db.run(employeeTableQuery.schema.create)

    def insertRecord(emp: Employee): Future[Int] = db.run {
      employeeTableQuery.returning(employeeTableQuery.map(_.id)) += emp
    }

    def deleteRecordWithId(id: Int): Future[Int] = db.run {
      employeeTableQuery.filter(_.id === id).delete
    }

    def updateRecord(emp:Employee): Future[Int] =db.run {
      employeeTableQuery.filter(_.id === emp.id).update(emp)
    }

    def getAllRecords: Future[List[Employee]] = db.run {
      employeeTableQuery.to[List].result
    }

    def upsertRecord(emp:Employee): Future[Boolean] ={
      updateRecord(emp).map{
        res => if(res == 0) { insertRecord(emp) ; true} else false
      }.recover {
        case ex: Throwable => false
      }
    }

    def getAllInterns: Future[List[Employee]] = db.run {
      employeeTableQuery.to[List].filter(_.experience<1.0).result
    }

}

object ForEmployeeTable extends EmployeeComponent






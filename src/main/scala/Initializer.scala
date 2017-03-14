/**
  * unused imports may be used when you uncomment some of the commented code below.
  * hence persisted in code
  */
import com.edu.knoldus.component.ForEmployeeTable
import com.edu.knoldus.component.ForProjectTable
import com.edu.knoldus.component.ForDependentTable
import com.edu.knoldus.models.{Employee, Project,Dependents}
import com.edu.knoldus.component
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.language.postfixOps

object Initializer extends App{

 /**
   *   operations on Employee table,
   *   Uncomment to check functionality
   *   fill in "??" fields if required as per your table records
   */

 /*
  //CREATE new Employee table
  try Await.result(ForEmployeeTable createEmployeeTable,Duration.Inf)
  catch{ case e:Exception => e}

  //INSERT records to Employee Table,id's are autoincrement(not provided here)
  ForEmployeeTable insertRecord  Employee("aaa",0.0)
  ForEmployeeTable insertRecord  Employee("bbb",1.0)
  ForEmployeeTable insertRecord  Employee("ccc",1.2)
  ForEmployeeTable insertRecord  Employee("ddd",0.0)


  //DELETE, replace ?? with an id(Int) of record to delete
  ForEmployeeTable deleteRecordWithId ??

  //UPDATE a record by id, replace ?? with an id(Int) of record update
   Await.result(ForEmployeeTable updateRecord  Employee("eee",2.5,??),Duration.Inf)


  //fetch all records in employee table
  val allRecords = Await.result(ForEmployeeTable getAllRecords,Duration.Inf)
  println(allRecords)

  //UPSERT, update by id if id dosent exist,Insert record,replace ?? with an id(Int) of record to upsert
  Await.result(ForEmployeeTable upsertRecord Employee("yyy",2.0,??),Duration.Inf)


  //CUSTOM, get List of all Interns by there experience(==0)
  val listOfInterns = Await.result(ForEmployeeTable getAllInterns,Duration.Inf)
  println(listOfInterns)*/

 /**
   *   operations on Project table,
   *   Uncomment to check functionality
   *   fill in "??" fields if required as per your table records
   *   TAKE CARE OF FOREGIN KEY CONSTRAINTS APPLIED field (LeadsID),Table may restrict some update operations
   *   if they dosent correspond to primary key(id) constrain of Employee table
   */

  //CREATE new Project table
  /* try Await.result(ForProjectTable createProjectTable,Duration.Inf)
   catch{ case e:Exception => e}

  //INSERT records to Employee Table,id's are autoincrement(not provided here),
  //?? correspond to leadsid(foregin key) correspond to employee table primary key(id)
  ForProjectTable insertRecord  Project("aaa",10,??)
  ForProjectTable insertRecord  Project("bbb",5,??)
  ForProjectTable insertRecord  Project("ccc",8,??)
  ForProjectTable insertRecord  Project("ddd",12,??)

  //DELETE, replace ?? with an id(Int) of record to delete
  ForProjectTable deleteRecordWithId ??

  //UPDATE a record by id,
  //replace 1st ?? with an id (Int) correspond to employee table (Foregin key)
  //replace 2nd ?? with id (Int) of the record you wanna update
  Await.result(ForProjectTable updateRecord  Project("zzz",2,??,??),Duration.Inf)


  //fetch all records in project table
  val allProjectRecords = Await.result(ForProjectTable getAllRecords,Duration.Inf)
  println(allProjectRecords)

  //UPSERT, update by id if id dosent exist,Insert record,
  //replace 1st ?? with an id (Int) correspond to employee table (Foregin key)
  //replace 2nd ?? with id (Int) of the record you wanna update
  Await.result(ForProjectTable upsertRecord Project("mohit",2,??,??),Duration.Inf)


  //CUSTOM, get List of all big project with team size greater then 5
  val listOfBigProjects = Await.result(ForProjectTable getAllBigProjects,Duration.Inf)
  println(listOfBigProjects)*/

 /**
   *   operations on dependent table,
   *   Uncomment to check functionality
   *   fill in "??" fields if required as per your table records
   *   TAKE CARE OF FOREGIN KEY CONSTRAINTS APPLIED field (employeeReference),Table may restrict some update operations
   *   and cascade delete operation if they dosent correspond to primary key(id) constrain of Employee table
   */


 /*
 //CREATE new dependents table
 try Await.result(ForDependentTable createProjectTable,Duration.Inf)
 catch{ case e:Exception => e}

 //INSERT records to dependents Table,id's are autoincrement(not provided here),
 //?? correspond to employeeReference(Int)(foregin key) correspond to employee table primary key(id)
 ForDependentTable insertRecord  Dependents(??,"mrabc","son",Some(15))
 ForDependentTable insertRecord  Dependents(??,"mrpqr","father",Some(60))
 ForDependentTable insertRecord  Dependents(??,"mrxyz","father",None)

 //DELETE, replace ?? with an id(Int) of record to delete
 ForDependentTable deleteRecordWithId ??

 //UPDATE a record by id,
 //replace 1st ?? with an id (Int) correspond to employee table (Foregin key)
 //replace 2nd ?? with id (Int) of the record you wanna update
 ForDependentTable updateRecord Dependents(??,"mrabc","father",Some(60),??)

 //fetch all records in dependents table
 val allDependentRecords = Await.result(ForDependentTable getAllRecords,Duration.Inf)
 println(allDependentRecords)

 //UPSERT, update by id if id dosent exist,Insert record,
 // replace 1st  ?? with an id(Int) correspond to employee table primary key(Id)
 // replace 2nd  ?? with an id(Int) of record to upsert
 Await.result(ForDependentTable upsertRecord Dependents(??,"mrxyz","father",Some(60),??),Duration.Inf)

 //CUSTOM, get List of all dependents who are senior citizens(>=60(age))
 val listOfSeniorCitizen = Await.result(ForDependentTable getAllSeniorCitizenDependents,Duration.Inf)
 println(listOfSeniorCitizen)



*/

  
  Thread.sleep(1000)
}

package com.edu.knoldus.component

import com.edu.knoldus.connection.H2DBComponent
import com.edu.knoldus.models.Project
import org.scalatest.AsyncFunSuite

class ProjectTableTest extends AsyncFunSuite{

  object tasty extends ProjectComponent with H2DBComponent

  test("Add new project") {
    tasty.insertRecord(Project("proName",12,5)).map(x=>assert(x == 4))
  }

  test("update Project record ") {
    tasty.updateRecord(Project("changedName",10,5,2)).map(x=>assert(x == 1))
  }

  test("delete project ") {
    tasty.deleteRecordWithId(2).map(x=>assert(x == 1))
  }

  test("getALL Projects") {
    tasty.getAllRecords.map(x=>assert(x.length==3))
  }

  test("upsert record") {
    tasty.upsertRecord(Project("changedName",10,5,2)).map(x=>assert(x===true))
  }

  test("get all big projects") {
    tasty.getAllBigProjects.map(x=>assert(x.length==2))
  }

  test("cross join between employee and project table") {
    tasty.crossJoin.map(x=>assert(x.length==9))
  }

  test("zip between employee and project table") {
    tasty.zipJoinQuery.map(x=>assert(x.length==3))
  }

  test("mximum team counts between different projects") {
    tasty.teamCount.map(x=>assert(x===Some(40)))
  }

  test("insert and then update project name") {
    tasty.defaultProjectName(Project("changedName",10,5,2)).map(x=>assert(x===1))
  }

  test("insert entries transctionally") {
    tasty.transaction(Project("changedName",10,5,2)).map(x=>assert(x===5))
  }

  test("plainsql") {
    tasty.insertPlainSql.map(x=>assert(x===1))
  }

  test("object return on insert rather then value") {
    tasty.insertRecordObj(Project("aaa",10,5,4)).map(x=>assert(x===Project("aaa",10,5,4)))
  }

}



package com.edu.knoldus.models

case class Dependents (
                         employeeReference:Int,
                         name:String,
                         relation:String,
                         age:Option[Int],
                         id:Int = 0
                      )



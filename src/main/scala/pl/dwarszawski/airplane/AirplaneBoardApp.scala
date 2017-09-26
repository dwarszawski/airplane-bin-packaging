package pl.dwarszawski.airplane

import pl.dwarszawski.airplane.model.CustomerImplicits.ToCustomerGroup
import pl.dwarszawski.airplane.model.Plane

import scala.io.Source

object AirplaneBoardApp extends App {

  val input = Source.fromResource("test1").getLines().toList
  val plane = Plane(input.head.split(" "))

  val rawGroups = input.drop(1).map(a => a.split(" "))
  val customerGroup = rawGroups.sortBy(_.length).reverse.map(_.toCustomerGroup)

  val boardedCustomers = plane.boardGroup(customerGroup, List.empty, List.empty)

  val rowCustomers = boardedCustomers.groupBy(_.row).toList.sortBy(_._1)

  print(rowCustomers.map(row => row._2.sortBy(_.sit).map(_.id).mkString(" ")).mkString("\n"))
  println(s"\nSatisfaction is ${boardedCustomers.count(_.isSatisfied).toFloat / boardedCustomers.size * 100}%")
}
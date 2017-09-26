package pl.dwarszawski.airplane.model

object Plane {
  def apply(dimensions: Array[String]): Plane = {
    val numberOfSits = dimensions(0).toInt
    val numberOfRows = dimensions(1).toInt

    Plane(numberOfRows, numberOfSits)
  }
}

case class Plane(rows: Int, columns: Int) {
  var freeSeats = {
    val result = for {
      h <- 1 until (rows + 1)
    } yield h -> columns

    result.toMap
  }

  def boardGroup(groups: List[List[Customer]], boardedCustomers: List[BoardedCustomer], remainingGroup: List[Customer]): List[BoardedCustomer] = {
    groups match {
      case gr :: grs => {
        /*        println(s"remaining board $board")
                println(s"current group $gr")*/
        val maybeRow = freeSeats.find { row => gr.size <= row._2 }
        if (maybeRow.nonEmpty) {
          val foundRow = maybeRow.get
          freeSeats = freeSeats + (foundRow._1 -> (foundRow._2 - gr.size))
          boardGroup(grs, assignCustomerGroupToRow(gr, foundRow, columns) ::: boardedCustomers, remainingGroup)
        } else {
          boardGroup(grs, boardedCustomers, gr.map(_.copy(isRemaining = true)) ::: remainingGroup)
        }
      }
      case Nil if remainingGroup.nonEmpty => boardGroup(remainingGroup.map(List(_)), boardedCustomers, List.empty)
      case Nil if remainingGroup.isEmpty => boardedCustomers
    }
  }

  private def assignCustomerGroupToRow(gr: List[Customer], foundRow: (Int, Int), numberOfSits: Int) = {
    val custWithIndex = gr.sortBy(_.windowPreference).reverse.zipWithIndex

    for {
      (cust, index) <- custWithIndex
      sitIndex = foundRow._2 - index
    } yield BoardedCustomer(cust.id, isSatisfiedByWindowPreference(cust, sitIndex, numberOfSits), foundRow._1, sitIndex)
  }

  private def isSatisfiedByWindowPreference(customer: Customer, sitIndex: Int, numberOfSits: Int) = {
    if (customer.isRemaining)
      false
    else if (customer.noWindowPreference || hasWindowPreferenceSatisfied(customer, sitIndex, numberOfSits)) {
      true
    } else {
      false
    }
  }

  private def hasWindowPreferenceSatisfied(customer: Customer, sitIndex: Int, numberOfSits: Int): Boolean = {
    customer.windowPreference && List(1, numberOfSits).contains(sitIndex)
  }
}


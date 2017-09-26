package pl.dwarszawski.airplane.model

case class Customer(id: String, windowPreference: Boolean, isRemaining: Boolean = false) {
  def noWindowPreference = !windowPreference
}

object Customer {
  def apply(input: String): Customer = {
    Customer(id = input, windowPreference = input.contains("W"))
  }
}

object CustomerImplicits {

  implicit class ToCustomerGroup(rawGroup: Array[String]) {
    def toCustomerGroup = rawGroup.map(Customer(_)).toList
  }

}


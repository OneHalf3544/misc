package lottery

import java.util.Scanner

import scala.collection.immutable


object Lottery {

  def main(args: Array[String]): Unit = {
    val resourceName = args.headOption.getOrElse("input.txt")
    calculateResultFor(resourceName).foreach(println(_))
  }

  def calculateResultFor(resourceName: String): immutable.List[String] = {
    val scanner = scannerFromResource(resourceName)

    val numbers = new ChosenNumbers(readLineNumbers(scanner))

    val ticketsCount = scanner.nextLine().toLong

    val result = (1L to ticketsCount)
      .map(i => new Ticket(readLineNumbers(scanner)))
      .map(numbers.isTicketWon)
      .map(if (_) "Lucky" else "Unlucky")
      .toList

    scanner.close()

    result
  }

  private def scannerFromResource(resourceName: String) =
    new Scanner(getClass.getResourceAsStream(resourceName))

  private def readLineNumbers(scanner: Scanner): List[Long] =
    scanner.nextLine().split(' ').map(_.toLong).toList
}

class Ticket(_numbers: List[Long]) {

  val numbers: List[Long] = _numbers.sorted
}

class ChosenNumbers(val numbers: List[Long]) {

  def isTicketWon(ticket: Ticket): Boolean = {
    var declaredIndex = 0
    var ticketIndex = 0
    var found = 0

    while (ticketIndex < ticket.numbers.size
      && declaredIndex < numbers.size
      && found < 3) {

      val ticketN = ticket.numbers(ticketIndex)
      val declaredN = numbers(declaredIndex)

      if (ticketN > declaredN) {
        declaredIndex += 1

      } else if (ticketN < declaredN){
        ticketIndex += 1

      } else {
        found += 1
        ticketIndex += 1
        declaredIndex += 1
      }
    }

    found >= 3
  }
}

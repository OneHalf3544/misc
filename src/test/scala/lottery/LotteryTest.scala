package lottery

import java.io.{BufferedReader, InputStreamReader}

import org.scalatest.{FlatSpec, Matchers}

class LotteryTest extends FlatSpec with Matchers {

  it should "print correct result for ordinal test case" in {
    val result = Lottery.calculateResultFor("input1.txt")

    result shouldBe loadExpectedResult("expected-output.txt")
  }

  private def loadExpectedResult(resourceName: String) =
    new BufferedReader(new InputStreamReader(getClass.getResourceAsStream(resourceName)))
      .lines()
      .toArray
      .toList
}

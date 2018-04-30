class FizzBuzz {
    fun fizzBuzz(n: Int): List<String> =
            (1..n).map {
                when {
                    it % 15 == 0 -> "FizzBazz"
                    it % 5 == 0 -> "Bazz"
                    it % 3 == 0 -> "Fizz"
                    else -> it.toString()
                }
            }
}


fun main(args: Array<String>) {
    println(FizzBuzz().fizzBuzz(35))
}
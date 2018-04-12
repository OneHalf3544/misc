fun factorial(n: Int): Long = (1..n).fold(1L, {acc, i -> acc * i})

fun main(args: Array<String>) {
    println(factorial(50))
}

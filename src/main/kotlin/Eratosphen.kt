import java.util.*

/**
 * Print primary numbers from 2 to n-th number.
 * First cell in @param args may contain a n-number or 200 will be used as default value
 */
fun main(args: Array<String>) {

    val n = if (args.isEmpty()) 200 else args[0].toInt()

    val primaryNumbers = BitSet().apply { set(2, n, true); }

    (2 .. Math.sqrt(n.toDouble()).toInt())
            .flatMap { 2 * it.. n step it }
            .forEach { primaryNumbers[it] = false }

    println(primaryNumbers)
}
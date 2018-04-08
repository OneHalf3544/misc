import kotlin.coroutines.experimental.buildSequence


fun main(arg: Array<String>) {

    val seq = buildSequence {
        for (i in 1..5) {
            // yield a square of i
            yield(i * i)
        }
        // yield a range
        yieldAll(26..28)
    }

    // print the sequence
    println(seq.toList())
}

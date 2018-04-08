import Direction.*
import java.awt.Point
import java.awt.Rectangle

class SpiralMartix(val matrix: Array<IntArray>) {

    var bounds = Rectangle(matrix[0].size, matrix.size)
    var x = -1; var y = 0;
    var direction = LEFT

    fun solve(): List<Int> {
        val result = arrayListOf<Int>()
        for (i in 1..(bounds.height * bounds.width)) {
            val nextPosition = nextPosition()
            x = nextPosition.x; y = nextPosition.y
            result += matrix[y][x]

            if (!bounds.contains(nextPosition())) {
                direction = direction.next()
                bounds = newBounds();
            }
        }
        assert(bounds.isEmpty, {"permitted area is not empty: $bounds"})
        assert(result.distinct().size == result.size, {"contains not unique elements: $result"})
        return result;
    }

    private fun newBounds(): Rectangle = when(direction) {
        DOWN -> Rectangle(bounds.x, bounds.y + 1, bounds.width, bounds.height - 1)
        RIGHT -> Rectangle(bounds.x, bounds.y, bounds.width - 1, bounds.height)
        UP -> Rectangle(bounds.x, bounds.y, bounds.width, bounds.height - 1)
        LEFT -> Rectangle(bounds.x + 1, bounds.y, bounds.width - 1, bounds.height)
    }

    private fun nextPosition(): Point = when (direction) {
        LEFT -> Point(x + 1, y)
        DOWN -> Point(x, y + 1)
        RIGHT -> Point(x - 1, y)
        UP -> Point(x, y - 1)
    }
}

enum class Direction(private val next: () -> Direction) {
    LEFT({DOWN}), DOWN({RIGHT}), RIGHT({UP}), UP({LEFT});

    fun next() = next.invoke()
}

fun main(args: Array<String>) {
    printFor(arrayOf(
            intArrayOf( 1, 2, 3, 4),
            intArrayOf( 5, 6, 7, 8),
            intArrayOf( 9,10,11,12),
            intArrayOf(13,14,15,16)
    ))

    printFor(arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
    ))

    printFor(arrayOf(
            intArrayOf(3, 2)
    ))

    printFor(arrayOf(
            intArrayOf(3),
            intArrayOf(2)
    ))
}

private fun printFor(matrix: Array<IntArray>) {
    println(SpiralMartix(matrix).solve())
}
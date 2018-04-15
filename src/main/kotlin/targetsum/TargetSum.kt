package targetsum

import java.lang.Math.abs
import java.util.*

class Solution {

    fun findTargetSumWays(nums: IntArray, S: Int): Int {
        println("${Arrays.toString(nums)}, $S")
        if (nums.size == 1) {
            return if (abs(nums[0]) == abs(S)) {
                if (S == 0) 2 else 1
            } else {
                0
            }
        }
        val subArray = nums.sliceArray(1..nums.lastIndex)
        return findTargetSumWays(subArray, S - nums[0]) + findTargetSumWays(subArray, S + nums[0])
    }

}

fun main(args: Array<String>) {
    printFor(intArrayOf(1, 0), 1)
    printFor(intArrayOf(1), 1)
    printFor(intArrayOf(1, 1), 0)
    printFor(intArrayOf(1, 1, 1), 1)
    printFor(intArrayOf(1, 1, 1, 1, 1), 3)
}

private fun printFor(ints: IntArray, target: Int) {
    val result = Solution().findTargetSumWays(ints, target)
    println("nums: ${Arrays.toString(ints)}, target: $target, result: $result")
}
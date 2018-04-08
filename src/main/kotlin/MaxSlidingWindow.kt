/*class Solution {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        return MaxSlidingWindow().calculate(nums, k)
    }
}*/

// https://leetcode.com/problems/sliding-window-maximum/description/
class MaxSlidingWindow {

    fun calculate(nums: IntArray, k: Int): IntArray {
        assert(nums.size >= k)

        if (nums.isEmpty()) {
            return intArrayOf()
        }
        val result = IntArray(nums.size - k + 1)
        result[0] = nums.take(k).max()!!

        for (beginIndex in 1 until result.size) {
            // if a new number bigger that last maximum, use it:
            if (nums[beginIndex + k - 1] > result[beginIndex - 1]) {
                result[beginIndex] = nums[beginIndex + k - 1]
                continue
            }
            // if a last bigger number is not gone, use the old value:
            if (nums[beginIndex - 1] < result[beginIndex - 1]) {
                result[beginIndex] = result[beginIndex - 1]
                continue
            }
            // calculate new max otherwise:
            result[beginIndex] = nums.drop(beginIndex).take(k).max()!!
        }
        return result
    }
}

fun main(args: Array<String>) {
    val message = MaxSlidingWindow().calculate(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7, 3), 3).toList().toString()
    val expection = "[3, 3, 5, 5, 6, 7, 7]"
    assert(message == expection, {"expected '$expection', but was $message"})
    println(message)
}
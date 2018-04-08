class ProductExceptSelf {
    fun productExceptSelf(nums: IntArray): IntArray {
        val leftProduct = IntArray(nums.size);
        val rightProduct = IntArray(nums.size);

        leftProduct[0] = nums.first()
        rightProduct[rightProduct.lastIndex] = nums.last()

        for (i in 1 until nums.lastIndex) {
            leftProduct[i] = nums[i] * leftProduct[i - 1]
            val j = rightProduct.lastIndex - i
            rightProduct[j] = nums[j] * rightProduct[j + 1]
        }

        return nums.indices
                .map { get(leftProduct, it - 1) * get(rightProduct, it + 1) }
                .toIntArray()
    }

    private fun get(array: IntArray, index: Int): Int = if (index in array.indices) array[index] else 1

}

fun main(args: Array<String>) {
    println(ProductExceptSelf().productExceptSelf(intArrayOf(1, 2, 3, 4)).toList())
}
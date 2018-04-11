import java.lang.Double.parseDouble

class Solution {
    fun isNumber(s: String): Boolean {
        try {
            parseDouble(s.trim())
            return !s.contains(Regex("[A-DF-Za-df-z]"))
        } catch(e: Exception) {
            return false
        }
    }
}

fun main(args: Array<String>) {
    println(Solution().isNumber("3"))
    println(Solution().isNumber("2e0"))
}
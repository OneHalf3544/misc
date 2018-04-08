fun main(args: Array<String>) {
    printResultFor("abcdefghij", "dce")
    printResultFor("abcdefghij", "ac")
    printResultFor("abcdefghij", "abcdefghij")
    printResultFor("a", "a")
    printResultFor("a", "b")
}

private fun printResultFor(string: String, substring: String) {
    (SearchPalindrome().search(string, substring)
            ?.apply { println("substring at index $first is palindrome: $second and $substring") }
            ?: "not found".apply { println(this) })
}

class SearchPalindrome {

    fun search(string: String, substring: String): Pair<Int, String>? {
        val expected = stringToMap(substring)
        val window = stringToMap(string.substring(0..substring.lastIndex))

        if (window == expected) {
            return Pair(0, string)
        }

        for (i in substring.length until string.lastIndex) {
            window.add(string[i])
            window.remove(string[i - substring.length])
            if (window == expected) {
                return Pair(i, string.substring(i - substring.length, i))
            }
        }
        return null
    }

    private fun stringToMap(s: String): Multiset<Char> {
        val result = Multiset<Char>()
        for (c in s) {
            result.add(c)
        }
        return result
    }
}

class Multiset<E> {

    val content = hashMapOf<E, Int>()

    fun add(value: E) {
        content.compute(value, { _, count -> 1 + (count ?: 0) })
    }

    fun contains(value: E): Boolean = content.containsKey(value)

    fun remove(value: E)  {
        content.compute(value, { _, count -> if (count == 1) null else (count!! - 1) })
    }

    val values
        get() = content.keys

    override fun equals(other: Any?): Boolean {
        return other != null
                && other is Multiset<*>
                && other.content == this.content
    }
}

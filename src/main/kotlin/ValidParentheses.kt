import java.util.*


class ValidParentheses {

    val openBracket = mapOf(
            '}'  to '{',
            ')'  to '(',
            ']'  to '[',
            '>'  to '<'
    )

    fun isValid(string: String):Boolean {
        val stack = LinkedList<Char>()
        for (c in string) {
            val o = openBracket[c]
            if (o != null) {
                if (stack.isEmpty() || stack.pop() != o) {
                    return false
                }
            } else {
                stack.push(c)
            }
        }

        return stack.isEmpty()
    }

}


fun main(args: Array<String>) {
    assertCorrect("")
    assertCorrect("{}")
    assertCorrect("()")
    assertCorrect("[]")
    assertCorrect("[]()")
    assertCorrect("([])")
    assertCorrect("({}[])")

    assertIncorrect("(")
    assertIncorrect(")")
    assertIncorrect("([)]")
    assertIncorrect("([]{)}")
    assertIncorrect("([]{}")
    assertIncorrect("([}{])")
}


fun assertCorrect(string: String) {
    assert(string, true);
}

fun assertIncorrect(string: String) {
    assert(string, false)
}

private fun assert(string: String, expectedValid: Boolean) {
    val valid = ValidParentheses().isValid(string)
    if (valid != expectedValid) {
        val expected = if (expectedValid) "valid" else "invalid"
        throw AssertionError("'$string' should be considered as $expected parentheses combination")
    }
}
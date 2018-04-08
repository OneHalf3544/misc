import java.util.*

// https://leetcode.com/problems/min-stack/description/
class MinStack() {

    val stack = LinkedList<Int>()
    val minStack = LinkedList<Int>()


    fun push(value: Int) {
        stack.push(value)
        if (minStack.isEmpty() || getMin() >= value) {
            minStack.push(value)
        }
    }

    fun pop() {
        val value = stack.pop()
        if (getMin() == value) {
            minStack.pop()
        }
    }

    fun top(): Int = stack.peek()

    fun getMin(): Int = minStack.peek()

}

fun main(args: Array<String>) {
    val minStack = MinStack();
    minStack.push(-2)
    minStack.push(0)
    minStack.push(-3)
    assert(minStack.getMin() == -3)
    minStack.pop()
    assert(minStack.top() == 0)
    assert(minStack.getMin() == -2)
}
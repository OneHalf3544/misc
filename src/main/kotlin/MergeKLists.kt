fun main(args: Array<String>) {
    val list = Solution2().mergeKLists(
            arrayOf<ListNode?>(
                list(1, 2, 3),
                list(2, 3, 4),
                list(0, 2, 5),
                null as ListNode?
            )
    )
    println(list)
}

fun list(vararg i: Int): ListNode? {
    return i.map { ListNode().apply { `val` = it } }
            .foldRight(null, { node, acc: ListNode? -> node.next = acc; node })
}

/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int = 0) {
 *     var next: ListNode? = null
 * }
 */
class Solution2 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        return when (lists.size) {
            0 -> return null
            1 -> lists[0]
            2 -> merge2Lists(lists[0], lists[1])
            else -> merge2Lists(
                    mergeKLists(lists.sliceArray(0 until lists.size / 2)),
                    mergeKLists(lists.sliceArray(lists.size / 2 until lists.size))
            )
        }
    }

    private fun merge2Lists(listNode1: ListNode?, listNode2: ListNode?): ListNode? {
        when {
            listNode1 == null -> return listNode2
            listNode2 == null -> return listNode1
        }
        val result = if (listNode1!!.`val` < listNode2!!.`val`) {
            listNode1.next = merge2Lists(listNode1.next, listNode2)
            listNode1
        } else {
            listNode2.next = merge2Lists(listNode1, listNode2.next)
            listNode2
        }
        return result
    }
}

class ListNode(var `val`: Int = 0) {
    var next: ListNode? = null

    override fun toString(): String {
        return `val`.toString() + " " + next?.toString()
    }
}
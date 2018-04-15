package balancedtree

import java.lang.Integer.max
import java.lang.Integer.min
import java.util.*
import kotlin.math.abs

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun toString(): String = "{$`val`: {$left, $right}}"
}

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 * 
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {

    fun isBalanced(root: TreeNode?): Boolean {
        if (root == null) {
            return true
        }
        val heights = getHeights(root)

        return heights != null && heights.second - heights.first <= 1
    }

    fun getHeights(node: TreeNode?): Pair<Int, Int>? {
        if (node == null) {
            return Pair(0, 0)
        }
        val leftHeights = getHeights(node.left)
        val rightHeights = getHeights(node.right)

        if (leftHeights == null || rightHeights == null) {
            return null
        }

        val minHeight: Int = max(leftHeights.first, rightHeights.first) + 1
        val maxHeight: Int = max(leftHeights.second, rightHeights.second) + 1
        val result = if (Math.abs(leftHeights.second - rightHeights.second) > 1) null else Pair(minHeight, maxHeight)

        println("node: $node, result: $result")
        return result
    }
}

class WrongSolution {
    var minHeight: Int? = null
    val queue = LinkedList<Pair<TreeNode, Int>>()

    fun isBalanced2(root: TreeNode?): Boolean {
        if (root == null) {
            return true
        }
        queue.addLast(Pair(root, 0))
        while (queue.isNotEmpty()) {
            val pair = queue.pollFirst()
            val node = pair.first
            val level = pair.second

            if (minHeight == null) {
                if (node.left == null || node.right == null) {
                    minHeight = pair.second
                }
            } else {
                if (level - minHeight!! > 1) {
                    return false
                }
            }
            node.left?.let { queue.addLast(Pair(it, level + 1)) }
            node.right?.let { queue.addLast(Pair(it, level + 1)) }
        }
        return true
    }
}
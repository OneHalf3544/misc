package balancedtree

import spock.lang.Specification

class SolutionTest extends Specification {
    def "IsBalanced"() {
        given: "balanced tree"
        def root = new TreeNode(3).with {
            left = new TreeNode(9)
            right = new TreeNode(20).with {
                left = new TreeNode(15)
                right = new TreeNode(7)
                it
            }
            it
        }

        when: "check for balancing"
        def result = new Solution().isBalanced(root)

        then: "result is true"
        result
    }

    /**
     * Check, that this tree is considered as balanced:
     *
     *         ____1____
     *        /         \
     *       2           2
     *      /  \        / \
     *     3    3      3   3
     *    /\    /\    /\
     *   4  4  4  4  4  4
     *  /\
     * 5  5
     *
     * Because the definition of balanced tree says about difference of height of two subtrees,
     * not about min and max height in the whole tree.
     *
     */
    def "IsBalanced2"() {
        given: "balanced tree"
        def root = new TreeNode(1).with {
            left = new TreeNode(2).with {
                left = new TreeNode(3).with {
                    left = new TreeNode(4).with {
                        left = new TreeNode(5)
                        right = new TreeNode(5)
                        it
                    }
                    right = new TreeNode(4)
                    it
                }
                right = new TreeNode(3).with {
                    left = new TreeNode(4)
                    right = new TreeNode(4)
                    it
                }
                it
            }
            right = new TreeNode(2).with {
                left = new TreeNode(3).with {
                    left = new TreeNode(4)
                    right = new TreeNode(4)
                    it
                }
                right = new TreeNode(3).with {
                    left = null
                    right = null
                    it
                }
                it
            }
            it
        }

        println(root)

        when: "check for balancing"
        def result = new Solution().isBalanced(root)

        then: "result is true"
        result
    }



}

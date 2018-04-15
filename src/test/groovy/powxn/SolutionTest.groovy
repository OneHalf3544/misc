package powxn

import spock.lang.Specification
import spock.lang.Unroll

class SolutionTest extends Specification {

    @Unroll
    def "myPow(#x, #n) == #expectation"() {
        when:
        def result = new Solution().myPow(x as Double, n)

        then:
        result == expectation

        where:
        x   | n           || expectation
        1   | -2147483648 || 1.0
        10  | 2           || 100
        10  | -2          || 0.01
        2.0 | -2147483648 || 0.0
        2.0 | -2147483647 || 0.0
    }
}

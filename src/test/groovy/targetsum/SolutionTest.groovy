package targetsum

import spock.lang.Specification

class SolutionTest extends Specification {
    def "FindTargetSumWays"() {
        when:
        def ways = new Solution().findTargetSumWays([1, 1, 1, 1, 1] as int[], 3)

        then:
        ways == 5
    }
}

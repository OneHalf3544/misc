package targetsum

import spock.lang.Specification

class SolutionTest extends Specification {
    def "FindTargetSumWays"() {
        when:
        def ways = new Solution().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3)

        then:
        ways == 5
    }
}

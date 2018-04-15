package numbertotitle

import spock.lang.Specification
import spock.lang.Unroll

class SolutionTest extends Specification {

    @Unroll
    def 'then number is #number, title should be #title'() {
        when:
        def result = new Solution().convertToTitle(number)

        then:
        result == title

        where:
        number || title
        1      || "A"
        2      || "B"
        26     || "Z"
        27     || "AA"
        28     || "AB"
//        702    || "YZ"
//        728    || "ZZ"
//        17550  || "ZYZ"
//        17576  || "ZZZ"
    }

    @Unroll
    def 'number #number maps to letter "#letter" correctly'() {
        when:
        def result = new Solution().mapNumber(number)

        then:
        result == letter as Character

        where:
        number || letter
        0      || "A"
        1      || "B"
        25     || "Z"
    }
}

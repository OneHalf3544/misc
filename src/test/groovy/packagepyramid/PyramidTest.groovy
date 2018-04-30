package packagepyramid

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class PyramidTest extends Specification {

    def 'a pyramid with four rows should contain ten cells'() {
        expect:
        new Pyramid(1).arraySizeByHeight(4) == 10
    }

    def 'content of row = #row, col = #col should be placed in #expected-th cell'() {
        expect:
        new Pyramid(1).index(row, col) == expected

        where:
        row | col || expected
        1   | 1   || 0
        3   | 1   || 3
        3   | 2   || 4

    }

    def 'a pyramid with one row should contain 1 as a root'() {
        given:
        def pyramid = new Pyramid(1)

        expect:
        pyramid.get(1, 1) == 1
    }

    def 'a pyramid with two rows should contain 1 in all cells'() {
        given:
        def pyramid = new Pyramid(2)

        expect:
        pyramid.get(1, 1) == 1
        pyramid.get(2, 1) == 1
        pyramid.get(2, 2) == 1
    }

    def 'toString of a pyramid with two rows should have a correct value'() {
        given:
        def pyramid = new Pyramid(2)

        expect:
        pyramid.toString() == "1 \n1 1 \n"
    }

    def 'toString of a pyramid with three rows should have a correct value'() {
        given:
        def pyramid = new Pyramid(3)

        expect:
        pyramid.toString() == "1 \n1 1 \n1 2 1 \n"
    }

    def 'toString of a pyramid with five rows should have a correct value'() {
        given:
        def pyramid = new Pyramid(5)

        expect:
        pyramid.toString() == """1 
                               |1 1 
                              |1 2 1 
                             |1 3 3 1 
                            |1 4 6 4 1 
                           |""".stripMargin("|")
    }
}

package packagepyramid

import spock.lang.Specification
import spock.lang.Timeout
import spock.lang.Unroll

import java.time.Duration
import java.time.Instant

@Unroll
class PyramidTest extends Specification {

    def startTime;

    def setup() {
        startTime = Instant.now()
    }

    def cleanup() {
        println "duration: ${Duration.between(startTime, Instant.now())}"
    }

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
        pyramid.getTask(1, 1).get() == 1
    }

    def 'a pyramid with two rows should contain 1 in all cells'() {
        given:
        def pyramid = new Pyramid(2)

        expect:
        pyramid.getTask(1, 1).get() == 1
        pyramid.getTask(2, 1).get() == 1
        pyramid.getTask(2, 2).get() == 1
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

    //@Timeout(10)
    def 'toString of a pyramid with hundred rows should have a correct value'() {
        when:
        def pyramid = new Pyramid(30)

        then:
        pyramid.getTask(30, 15).get() == 77558760
    }
}

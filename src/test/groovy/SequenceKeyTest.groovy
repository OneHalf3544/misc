import org.spockframework.util.IoUtil
import spock.lang.Specification
import spock.lang.Unroll

class SequenceKeyTest extends Specification {

    @Unroll
    def "Calculate for data from #inputResource"() {
        given: "input data"
        def sequence = new SequenceKey(getClass().getResourceAsStream("sequence/$inputResource"))
        def output = new ByteArrayOutputStream()

        when: "calculate result"
        sequence.calculateAndPrint(new PrintStream(output))

        then: "correct response was written"
        output.toString().trim() == stringFromResource(expected)

        where:
        inputResource | expected
        'input1.txt' | 'expected1.txt'
        'input2.txt' | 'expected2.txt'
    }

    private String stringFromResource(resourceName) {
        IoUtil.getText(getClass().getResourceAsStream("sequence/$resourceName")).trim()
    }
}

package lottery;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class LotteryJavaTest {

    @Test
    public void should_printCorrectResult_when_isOrdinalTestCase() {
        List<String> result = new LotteryJava().calculateResultFor("input1.txt");

        assertThat(result, equalTo(loadExpectedResult("expected-output.txt")));
    }

    private List<String> loadExpectedResult(String resourceName) {
        return new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resourceName)))
                .lines()
                .collect(toList());
    }
}

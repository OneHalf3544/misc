import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class FibonachiMemoization {

    private static final Map<Integer, Long> cache = new HashMap<>();
    static {
        cache.put(1, 1L);
        cache.put(2, 1L);
    }

    public static void main(String[] args) {
        System.out.print(fibonachi(40));
    }

    private static long fibonachi(int number) {
        assert number > 0 : "number should be more than zero";
        return cache.computeIfAbsent(number, n -> fibonachi(n - 2) + fibonachi(n - 1));
    }
}

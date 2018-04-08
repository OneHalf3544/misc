import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class Fibonachi {

    private static final Map<Integer, Long> cache = new HashMap<>();
    static {
        cache.put(1, 1L);
        cache.put(2, 1L);
    }

    public static void main(String[] args) {
        System.out.print(sumOfFirst(100_000));
    }

    private static long fibonachi(int number) {
        assert number > 0 : "number should be more than zero";
        return cache.computeIfAbsent(number, n -> fibonachi(n - 2) + fibonachi(n - 1));
    }

    private static long sumOfFirst(int number) {
        Supplier<?> supplier = new MySupplier(number, 0);

        Object result = supplier;
        do {
            result = ((Supplier<Object>) result).get();
        } while (result instanceof Supplier);

        return (Long) result;
    }

    private static class MySupplier implements Supplier<Object> {

        private final long number;
        private final long accumulator;

        public MySupplier(long number, long accumulator) {
            this.number = number;
            this.accumulator = accumulator;
        }

        @Override
        public Object get() {
            return number > 0 ? new MySupplier(number - 1, accumulator + number) : accumulator;
        }
    }
}

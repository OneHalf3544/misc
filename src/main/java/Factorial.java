import java.util.stream.IntStream;

public class Factorial {

    public static void main(String[] args) {
        System.out.println(factorial(6));
    }

    private static long factorial(int n) {
        return IntStream.rangeClosed(1, n).asLongStream().reduce(1L, (left, right) -> left * right);
    }
}

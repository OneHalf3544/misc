import java.util.stream.Stream;

public class FibbonachiStream {

    public static void main(String[] args) {
        System.out.println(fibbonachi(30));
    }

    private static Long fibbonachi(int n) {
        return Stream.iterate(new long[]{1, 1}, longs -> new long[]{longs[1], longs[0] + longs[1]})
                .map(longs -> longs[0])
                .skip(n - 1)
                .findFirst()
                .get();
    }
}

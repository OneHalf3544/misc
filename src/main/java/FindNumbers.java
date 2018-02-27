import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by onehalf on 03.10.17.
 */
public class FindNumbers {

    public static void main(String[] args) {
        long n = 50;

        System.out.println("bruteForce");
        bruteForce(n);

        System.out.println("hashmap");
        solve(n);
    }

    private static void solve(long n) {
        Map<Long, List<Pair>> cache = new HashMap<>();

        for (long a = 0; a < n; a++) {
            for (long b = a + 1; b < n; b++) {
                List<Pair> pairList = cache.computeIfAbsent(cube(a) + cube(b), sum -> new ArrayList<>());
                pairList.add(new Pair(a, b));
            }
        }

        for (List<Pair> pairs : cache.values()) {
            if (pairs.size() < 2) {
                continue;
            }
            for (int i = 0; i < pairs.size(); i++) {
                for (int j = i + 1; j < pairs.size(); j++) {
                    System.out.printf("a=%d, b=%d, c=%d, d=%d\n",
                            pairs.get(i).first, pairs.get(i).secong, pairs.get(j).first, pairs.get(j).secong);
                }
            }
        }
    }

    private static void bruteForce(long n) {
        for (long a = 0; a < n; a++) {
            for (long b = a + 1; b < n; b++) {
                for (long c = a + 1; c < n; c++) {
                    for (long d = c + 1; d < n; d++) {
                        if (cube(a) + cube(b) == cube(c) + cube(d)
                                && a != c
                                && a != d) {
                            System.out.printf("a=%d, b=%d, c=%d, d=%d\n", a, b, c, d);
                        }
                    }
                }
            }
        }
    }

    private static long cube(long a) {
        return a * a * a;
    }

    static class Pair {

        public Pair(long first, long secong) {
            this.first = first;
            this.secong = secong;
        }

        long first;
        long secong;
    }
}

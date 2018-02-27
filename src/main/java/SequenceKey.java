import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.stream.Collectors.toList;

public class SequenceKey {

    private final InputStream in;

    public SequenceKey(InputStream in) {
        this.in = in;
    }

    public static void main(String[] args) {
        new SequenceKey(System.in).calculateAndPrint(System.out);
    }

    void calculateAndPrint(PrintStream printStream) {
        List<List<Integer>> lists = loadData(in);

        Map<Integer, List<Integer>> map = new HashMap<>();
        AtomicReference<List<Integer>> reference = new AtomicReference<>();

        lists.forEach(line -> {
            if (reference.get() == null && map.containsKey(line.get(0))) {
                reference.set(line);
                return;
            }
            map.put(line.get(0), line);
        });

        reference.get().stream()
                .map(key -> Objects.requireNonNull(map.get(key), () -> "no value for key " + key))
                .forEach(line -> line.forEach(it -> printStream.print(it + " ")));
    }

    private List<List<Integer>> loadData(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        int n = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> sequences = new ArrayList<>(2 * n);
        for (int i = 0; i < 2 * n; i++) {
            sequences.add(parseNumbers(scanner.nextLine()));
        }
        return sequences;
    }

    private List<Integer> parseNumbers(String line) {
        return Arrays.stream(line.split(" "))
                .map(Integer::valueOf)
                .collect(toList());
    }
}

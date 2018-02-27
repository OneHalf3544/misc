import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toSet;

/**
 * Created by onehalf on 14.11.17.
 */
public class KeyImpl {

    private static int X;
    public static void main(final String... args) {
        System.out.println("RESULT: " + X++ + (X += 1));
    }
}
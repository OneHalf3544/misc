package lottery;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;


public class LotteryJava {

    public static void main(String... args) {
        new LotteryJava().calculateResultFor(System.in).forEach(System.out::println);
    }

    public List<String> calculateResultFor(String resourceName) {
        return calculateResultFor(getClass().getResourceAsStream(resourceName));
    }

    public List<String> calculateResultFor(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);

        ChosenNumbers numbers = new ChosenNumbers(readLineNumbers(scanner));

        long ticketsCount = Long.parseLong(scanner.nextLine());

        List<String> result = new ArrayList<>();
        for(int i = 0; i < ticketsCount; i++) {
            Ticket ticket = new Ticket(readLineNumbers(scanner));
            if (numbers.isTicketWon(ticket))
                result.add("Lucky");
            else
                result.add("Unlucky");
        }

        scanner.close();

        return result;
    }

    private List<Long> readLineNumbers(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split(" "))
                .map(Long::valueOf)
                .collect(toList());
    }

    class Ticket {
        final List<Long> numbers;

        Ticket(List<Long> numbers) {
            this.numbers = numbers.stream()
                    .sorted()
                    .collect(toList());
        }
    }

    class ChosenNumbers {

        private final List<Long> numbers;

        ChosenNumbers(List<Long> numbers) {
            this.numbers = numbers.stream()
                    .sorted()
                    .collect(toList());
        }

        boolean isTicketWon(Ticket ticket) {
            int declaredIndex = 0;
            int ticketIndex = 0;
            int found = 0;

            while (ticketIndex < ticket.numbers.size()
                    && declaredIndex < numbers.size()
                    && found < 3) {

                Long ticketN = ticket.numbers.get(ticketIndex);
                Long declaredN = numbers.get(declaredIndex);

                if (ticketN > declaredN) {
                    declaredIndex += 1;

                } else if (ticketN < declaredN) {
                    ticketIndex += 1;

                } else {
                    found += 1;
                    ticketIndex += 1;
                    declaredIndex += 1;
                }
            }

            return found >= 3;
        }
    }
}

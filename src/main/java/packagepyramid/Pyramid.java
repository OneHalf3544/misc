package packagepyramid;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Pyramid {

    private static final ExecutorService FORK_JOIN_POOL = Executors.newWorkStealingPool();

    private final Task[] content;
    private int height;

    Pyramid(int height) {
        this.height = height;
        content = new Task[arraySizeByHeight(height)];

        for (int row = 1; row <= height; row++) {
            for (int col = 1; col <= row; col++) {
                content[index(row, col)] = new Task(this, row, col);
            }
        }

        FORK_JOIN_POOL.submit(() -> {
            Stream.of(content).forEach(ForkJoinTask::fork);
        });
        Stream.of(content).forEach(ForkJoinTask::join);
    }

    Task getTask(int row, int column) {
        return content[index(row, column)];
    }

    private int arraySizeByHeight(int height) {
        return height * (height + 1) / 2;
    }

    private int index(int row, int column) {
        assert column <= row : String.format("row: %s, column: %s", row, column);
        return arraySizeByHeight(row - 1) + column - 1;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row = 1; row <= height; row++) {
            for (int col = 1; col <= row; col++) {
                result.append(content[index(row, col)].getRawResult()).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

}

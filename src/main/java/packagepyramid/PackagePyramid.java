package packagepyramid;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class PackagePyramid {

    public static void main(String... args) {

    }
}

class Pyramid {

    private static final ForkJoinPool FORK_JOIN_POOL = (ForkJoinPool) Executors.newWorkStealingPool();

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

        content[0] = new Task(this, 1, 1) {
            @Override
            protected Long compute() {
                return 1L;
            }
        };

        FORK_JOIN_POOL.invoke(new RecursiveAction() {
            @Override
            protected void compute() {
                List<ForkJoinTask<Long>> tasks = IntStream.rangeClosed(1, height)
                        .mapToObj(i -> getTask(height, i).fork())
                        .collect(toList());

                tasks.forEach(ForkJoinTask::join);
            }
        });
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

    class Task extends RecursiveTask<Long> {

        private final Pyramid pyramid;
        private final int row;
        private final int column;
        private Long result;

        Task(Pyramid pyramid, int row, int column) {
            this.pyramid = pyramid;
            this.row = row;
            this.column = column;
        }

        @Override
        protected Long compute() {
            if (result != null) {
                return result;
            }
            return result = computeInternal();
        }

        private Long computeInternal() {
            if (column == 1) {
                return forkTask(row - 1, 1).join();
            } else if (column == row) {
                return forkTask(row - 1, column - 1).join();
            } else {
                List<ForkJoinTask<Long>> tasks = List.of(
                        forkTask(row - 1, column - 1),
                        forkTask(row - 1, column));
                return tasks.stream()
                        .mapToLong(ForkJoinTask::join)
                        .sum();
            }
        }

        @NotNull
        private ForkJoinTask<Long> forkTask(int row, int column) {
            Task task = pyramid.getTask(row, column);
            if (task.isDone()) {
                return task;
            }
            return task.fork();
        }
    }

    @Override
    @SneakyThrows
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row = 1; row <= height; row++) {
            for (int col = 1; col <= row; col++) {
                result.append(content[index(row, col)].get()).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

}

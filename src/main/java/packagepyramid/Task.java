package packagepyramid;

import lombok.SneakyThrows;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

class Task extends RecursiveTask<Long> {

    private final Pyramid pyramid;
    private final int row;
    private final int column;

    Task(Pyramid pyramid, int row, int column) {
        this.pyramid = pyramid;
        this.row = row;
        this.column = column;
    }

    @Override
    protected Long compute() {
        if (column == 1 || column == row) {
            return 1L;
        }

        Task task1 = pyramid.getTask(row - 1, column);
        Task task2 = pyramid.getTask(row - 1, column - 1);

        return task1.join() + task2.join();
    }

}

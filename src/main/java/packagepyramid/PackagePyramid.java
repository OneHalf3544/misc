package packagepyramid;

public class PackagePyramid {

    public static void main(String... args) {

    }
}

class Pyramid {

    private final long[] content;
    private int height;

    Pyramid(int height) {
        this.height = height;
        content = new long[arraySizeByHeight(height)];
        set(1, 1, 1);

        for (int col = 1; col <= height; col++) {
            get(height, col);
        }
    }

    private void set(int row, int column, long value) {
        content[index(row, column)] = value;
    }

    long get(int row, int column) {
        long value = content[index(row, column)];
        if (value != 0) {
            return value;
        }

        if (column == 1) {
            value = get(row - 1, 1);
            set(row, column, value);
            return value;
        }
        if (column == row) {
            value = get(row - 1, column - 1);
            set(row, column, value);
            return value;
        }
        value = get(row - 1, column - 1) + get(row - 1, column);
        return value;
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
                result.append(get(row, col)).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}

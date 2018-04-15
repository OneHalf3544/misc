package powxn;

class Solution {
    public double myPow(double x, int n) {
        if (n == Integer.MIN_VALUE) {
            // because -Integer.MIN_VALUE == Integer.MIN_VALUE, we cannot just invert sign
            return (1 / x) * myPow(x, n + 1);
        }
        if (n < 0) {
            return 1 / myPow(x, -n);
        }
        if (n == 0) {
            return 1.0;
        }
        if (n == 1) {
            return x;
        }
        if (n == 2) {
            return x * x;
        }
        double subResult = myPow(x, n / 2);
        return subResult * subResult * (n % 2 == 0 ? 1.0 : x);
    }
}

package numbertotitle;

class Solution {

    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            char ch = mapNumber((n - 1) % 26);
            result.append(ch);
            n =  (n - 1) / 26;
        }
        return result.reverse().toString();
    }

    char mapNumber(int n) {
        assert n >= 0 && n <= 25;
        return (char) ('A' + n);
    }
}
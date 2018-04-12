package reversewords;

public class ReverseWords {



    public static void main(String[] args) {
        String result = new Solution().reverseWords("abc def ghi");
        assert "ghi def abc".equals(result) : "result: '" + result + "'";
        result = new Solution().reverseWords("   a   b ");
        assert "b a".equals(result) : "result: '" + result + "'";
    }
}


class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return "";
        }
        StringBuilder from = new StringBuilder(s);
        StringBuilder to = new StringBuilder(s.length());

        while (true) {
            int lastIndex = from.lastIndexOf(" ");
            if (lastIndex == -1) {
                to.append(from.toString().trim());
                break;
            }
            String word = from.substring(lastIndex + 1).trim();
            from.delete(lastIndex, from.length());
            if (!word.isEmpty()) {
                to.append(word).append(" ");
            }
        }

        return to.toString();
    }
}
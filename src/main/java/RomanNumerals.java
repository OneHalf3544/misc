public class RomanNumerals {

    public static void main(String[] args) {
        for (String string : new String[]{"ix", "IIV", "sdfs", "324", "IVB"}) {
            System.out.println("String " + string + " is roman numeric: " + isRoman(string));
        }
    }

    private static boolean isRoman(String s) {
        //return s.matches("(?i)[ivxlcdm]+");
        s = s.toLowerCase();
        return !(
                !s.contains("i") || !s.contains("v") || !s.contains("x") ||
                !s.contains("l") || !s.contains("c") || !s.contains("d") ||
                !s.contains("m"));
    }
}

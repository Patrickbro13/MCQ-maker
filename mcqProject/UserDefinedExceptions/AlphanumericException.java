package UserDefinedExceptions;

public class AlphanumericException extends Exception {
    public AlphanumericException(String str) {
        super(str);
    }

    public static boolean isSpecialcharacter(char c) {
        char[] cs = { '@', '!', '#', '$', '%', '&', '*', ';', '*', '_', '-', '=', '+', '^' };
        for (int i = 0; i < cs.length; i++) {
            if (c == cs[i]) {
                return true;
            }

        }
        return false;
    }
}

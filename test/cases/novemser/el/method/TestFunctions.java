package cases.novemser.el.method;

/**
 * Project: apache-tomcat-7.0.0-src
 * Package: cases.novemser.el.conversion
 * Author:  Novemser
 * 2017/5/30
 */
public class TestFunctions {
    public static String trim(String input) {
        return input.trim();
    }

    public static String getGreeting(String greeting) {
        return greeting;
    }

    public static String concat(String... inputs) {
        if (inputs == null || inputs.length == 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (String input : inputs) {
            result.append(input);
        }
        return result.toString();
    }

    public static String concat2(String prefix, String... inputs) {
        StringBuilder result = new StringBuilder(prefix);
        for (String input : inputs) {
            result.append(input);
        }
        return result.toString();
    }

    public static String[] toArray(String a, String b) {
        return new String[]{a, b};
    }

    public static void sout() {
        System.out.println("Hello");
    }

    public static void soutStr(String str) {
        System.out.println(str);
    }

    public static void soutStrs(String... strings) {
        for (String s : strings) {
            System.out.println(s);
        }
    }
}

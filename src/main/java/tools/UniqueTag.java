package tools;

public class UniqueTag {

    /**
     * Generate unique string.
     * @return Unique string based on build number and current time.
     */
    public static String generateString() {

        return "{" + System.currentTimeMillis() + "}";
    }
}

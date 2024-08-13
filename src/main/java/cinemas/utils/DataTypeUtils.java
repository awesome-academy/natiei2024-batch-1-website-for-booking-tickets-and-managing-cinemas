package cinemas.utils;

public class DataTypeUtils {
    public static Integer parseIntOrNull(String theaterId) {
        try {
            return Integer.parseInt(theaterId);
        } catch (NumberFormatException e) {
            return null; // Or handle this case as needed
        }
    }
}

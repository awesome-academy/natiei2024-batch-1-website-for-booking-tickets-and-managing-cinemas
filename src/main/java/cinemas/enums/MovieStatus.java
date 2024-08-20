package cinemas.enums;

public enum MovieStatus {
    NOW_SHOWING,
    COMING_SOON,
    END_SHOWING;

    /**
     * Returns the corresponding MovieStatus for a given string value.
     * If no matching value is found, returns null.
     *
     * @param value the string value to convert
     * @return the corresponding MovieStatus, or null if no match is found
     */
    public static MovieStatus fromValue(String value) {
        if (value == null) {
            return null;
        }

        for (MovieStatus status : MovieStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }

        return null;
    }
}
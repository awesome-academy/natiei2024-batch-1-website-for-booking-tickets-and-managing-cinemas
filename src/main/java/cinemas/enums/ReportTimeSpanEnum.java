package cinemas.enums;

public enum ReportTimeSpanEnum {
    WEEK,
    MONTH,
    YEAR,
    ALL_TIME;

    // Method to convert an integer to the corresponding enum value
    public static ReportTimeSpanEnum fromOrdinal(Integer ordinal) {
        if (ordinal == null) {
            return WEEK;
        }
        for (ReportTimeSpanEnum value : ReportTimeSpanEnum.values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        // Default value if ordinal is out of range
        return WEEK;
    }
}

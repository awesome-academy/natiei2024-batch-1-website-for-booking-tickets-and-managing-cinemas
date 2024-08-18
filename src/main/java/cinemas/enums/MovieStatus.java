package cinemas.enums;

public enum MovieStatus {
    NOW_SHOWING("Now Showing", "Đang chiếu"),
    COMING_SOON("Coming Soon", "Sắp chiếu"),
    END_SHOWING("End Showing", "Kết thúc chiếu");
    private String langEn;
    private String langVn;

    MovieStatus(String langEn, String langVn) {
        this.langEn = langEn;
        this.langVn = langVn;
    }

    public String getLangEn() {
        return langEn;
    }

    public String getLangVn() {
        return langVn;
    }

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

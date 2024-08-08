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
}

package cinemas.dtos;

import cinemas.models.Genre;

public class GenreDto {
    private String nameEn;
    private String nameVn;

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameVn() {
        return nameVn;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

    public Genre toGenre() {
        var genre = new Genre();
        genre.setNameEn(nameEn);
        genre.setNameVn(nameVn);
        return genre;
    }
}

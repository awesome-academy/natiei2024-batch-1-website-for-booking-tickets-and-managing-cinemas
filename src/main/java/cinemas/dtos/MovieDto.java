package cinemas.dtos;

import cinemas.enums.MovieStatus;
import cinemas.models.Genre;
import cinemas.models.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class MovieDto {
    private String titleVn;
    private String titleEn;
    private String languageVn;
    private String languageEn;
    private String descriptionVn;
    private String descriptionEn;
    private String releaseDate;
    private int runningTime; // minutes
    private int ageLimit;
    private String director;
    private String cast;
    private MovieStatus status;
    private String trailer;
    private MultipartFile photo;
    private List<Genre> genres;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getTitleVn() {
        return titleVn;
    }

    public void setTitleVn(String titleVn) {
        this.titleVn = titleVn;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getLanguageVn() {
        return languageVn;
    }

    public void setLanguageVn(String languageVn) {
        this.languageVn = languageVn;
    }

    public String getLanguageEn() {
        return languageEn;
    }

    public void setLanguageEn(String languageEn) {
        this.languageEn = languageEn;
    }

    public String getDescriptionVn() {
        return descriptionVn;
    }

    public void setDescriptionVn(String descriptionVn) {
        this.descriptionVn = descriptionVn;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public MovieStatus getStatus() {
        return status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Movie toMovie() {
        Movie movie = new Movie();
        movie.setDirector(director);
        movie.setCast(cast);
        movie.setTitleVn(titleVn);
        movie.setTitleEn(titleEn);
        movie.setLanguageVn(languageVn);
        movie.setLanguageEn(languageEn);
        movie.setDescriptionVn(descriptionVn);
        movie.setDescriptionEn(descriptionEn);
        movie.setReleaseDate(getReleaseDate() != null
                ? ZonedDateTime.parse(getReleaseDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm").withZone(ZoneId.of("Asia/Ho_Chi_Minh")))
                : null);
        movie.setRunningTime(runningTime);
        movie.setAgeLimit(ageLimit);
        movie.setStatus(status);
        movie.setTrailer(trailer);
        movie.setGenres(Set.copyOf(genres));
        return movie;
    }
}

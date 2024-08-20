package cinemas.models;

import cinemas.converters.ZonedDateTimeConverter;
import cinemas.enums.MovieStatus;
import cinemas.models.common.SoftDeletableEntity;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie extends SoftDeletableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String director;
    private String cast;
    @Column(name = "title_vn")
    private String titleVn;
    @Column(name = "title_en")
    private String titleEn;
    @Column(name = "language_vn")
    private String languageVn;
    @Column(name = "language_en")
    private String languageEn;
    @Lob
    @Column(name = "description_vn")
    private String descriptionVn;
    @Lob
    @Column(name = "description_en")
    private String descriptionEn;
    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "release_date")
    private ZonedDateTime releaseDate;
    @Column(name = "running_time")
    private Integer runningTime; // minutes
    private String trailer;
    @Column(name = "age_limit")
    private Integer ageLimit = 0; // Example: '18' is for age >= 18
    @Column(name = "photo_url")
    private String photoUrl;
    @Enumerated(EnumType.ORDINAL)
    private MovieStatus status = MovieStatus.COMING_SOON; // 0 = coming soon, 1 = now showing, 2 = end showing
    @ManyToMany(mappedBy = "movies",fetch = FetchType.EAGER)
    private Set<Genre> genres;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public ZonedDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ZonedDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Integer runningTime) {
        this.runningTime = runningTime;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
    public MovieStatus getStatus() {
        return status;
    }
    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
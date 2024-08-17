package cinemas.validators;

import cinemas.dtos.MovieCreateFormDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MovieCreateFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MovieCreateFormDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, org.springframework.validation.Errors errors) {
        MovieCreateFormDto form = (MovieCreateFormDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titleVn", "admin.insertMovieForm.require", new Object[]{"tên phim (VI)", "movie title (VI)"}, "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titleEn", "admin.insertMovieForm.require", new Object[]{"tên phim (EN)", "movie title (EN)"}, "");

        if (form.getGenres() == null || form.getGenres().isEmpty()) {
            errors.rejectValue("genres", "admin.insertMovieForm.emptyList.invalid", new Object[]{"thể loại", "genre"}, "");
        }
    }
}

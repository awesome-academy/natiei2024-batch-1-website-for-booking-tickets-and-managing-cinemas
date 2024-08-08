package cinemas.validators;

import cinemas.dtos.ScreenCreateFormDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("beforeCreateScreenValidator")
public class ScreenCreateFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ScreenCreateFormDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var screenCreateFormDto = (ScreenCreateFormDto) target;
        ValidationUtils.rejectIfEmpty(errors, "theaterId", "require", new Object[]{"Rạp"}, "Theater is required");
        ValidationUtils.rejectIfEmpty(errors, "name", "require", new Object[]{"Tên phòng chiếu"}, "Name is required");
        ValidationUtils.rejectIfEmpty(errors, "horizontalSize", "require", new Object[]{"Chiều ngang"}, "Horizontal size is required");
        ValidationUtils.rejectIfEmpty(errors, "verticalSize", "require", new Object[]{"Chiều dọc"}, "Vertical size is require");

        if (screenCreateFormDto.getHorizontalSize() != null && screenCreateFormDto.getHorizontalSize() < 0) {
            errors.rejectValue("horizontalSize", "negativeValue", new Object[]{"Chiều ngang"}, "Horizontal size cannot be negative or zero");
        }

        if (screenCreateFormDto.getVerticalSize() != null && screenCreateFormDto.getVerticalSize() < 0) {
            errors.rejectValue("verticalSize", "negativeValue", new Object[]{"Chiều dọc"}, "Vertical size cannot be negative or zero");
        }
    }
}

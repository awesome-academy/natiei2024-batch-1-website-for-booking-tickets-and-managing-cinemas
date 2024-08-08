package cinemas.exceptions;

import javassist.NotFoundException;

public class TheaterNotFoundException extends NotFoundException {
    public TheaterNotFoundException() {
        super("Theater not found");
    }
}

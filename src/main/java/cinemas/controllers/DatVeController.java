package cinemas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatVeController {
    @GetMapping("/datve")
    public String getDatVe() {
        return "user/datve";
    }
}

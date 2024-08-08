package cinemas.controllers.admin;

import cinemas.dtos.ScreenCreateFormDto;
import cinemas.exceptions.TheaterNotFoundException;
import cinemas.services.ScreensService;
import cinemas.validators.ScreenCreateFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/screens")
public class ScreensController {
    @Autowired
    private ScreenCreateFormValidator screenCreateFormValidator;
    @Autowired
    private ScreensService screensService;

    @InitBinder("screenCreateFormDto")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(screenCreateFormValidator);
    }

    @GetMapping
    public String index() {
        return "admin/screens/index";
    }

    @GetMapping("/new")
    public String createScreenForm(Model model) {
        model.addAttribute("screenCreateFormDto", new ScreenCreateFormDto());
        return "admin/screens/new";
    }

    @PostMapping
    public String createScreen(Model model,
                               @ModelAttribute("screenCreateFormDto") @Validated ScreenCreateFormDto screenCreateFormDto,
                               BindingResult binding) throws TheaterNotFoundException {
        if (binding.hasErrors()) {
            return "admin/screens/new";
        }
        screensService.create(screenCreateFormDto);
        return "redirect:/admin/screens";
    }
}

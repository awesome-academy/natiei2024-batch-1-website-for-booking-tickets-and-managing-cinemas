package cinemas.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {
    @GetMapping("/me/info")
    public String getMyPersonalInfomation(Model model) {
        model.addAttribute("section", "info");
        return "user/infos/show";
    }
    @GetMapping("/me")
    public String getMyAccountInformation(Model model) {
        model.addAttribute("section", "account");
        return "user/infos/index";
    }
}

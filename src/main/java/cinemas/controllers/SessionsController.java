package cinemas.controllers;

import cinemas.validators.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionsController {
    private static final Logger logger = LoggerFactory.getLogger(SessionsController.class);

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserValidator());
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.logout();
        } catch (ServletException e) {
            logger.error("Logout failed", e);
        }
        return "redirect:/";
    }
}

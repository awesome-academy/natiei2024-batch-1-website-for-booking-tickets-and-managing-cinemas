package cinemas.controller;

import cinemas.model.User;
import cinemas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        User user = new User(1L, "Minh", "HT");
        userRepository.save(user);
        Optional<User> userOptional = userRepository.findById(1L);
        if (userOptional.isPresent()) {
            User user1 = userOptional.get();
            System.out.println(user1);
        } else {
            System.out.println("User not found.");
        }


        model.addAttribute("name", "World");
        return "index"; // Refers to home.html in WEB-INF/views/
    }

}

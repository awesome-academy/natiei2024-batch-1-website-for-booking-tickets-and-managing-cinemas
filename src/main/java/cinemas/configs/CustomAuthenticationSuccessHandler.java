package cinemas.configs;

import cinemas.enums.RoleEnum;
import cinemas.models.User;
import cinemas.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private UserService userService;

    @Autowired
    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();
        session.setAttribute("user", userService.findByEmail(authentication.getName()));

        try {
            if (authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect(request.getContextPath() + "/admin/");
            } else {
                response.sendRedirect(request.getContextPath() + "/");
            }
        } catch (IOException ignored) {
        }
    }
}
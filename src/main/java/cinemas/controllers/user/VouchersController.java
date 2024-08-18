package cinemas.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vouchers")
public class VouchersController {
    @GetMapping("/me")
    public String getMyVouchers(Model model) {
        model.addAttribute("section", "voucher");
        return "user/vouchers/me/index";
    }
}

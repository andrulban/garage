package andruha_pgs.garage.controllers.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping(value = "/login-page")
    public String getLoginPage() {
        return "/pages/login.html";
    }
}

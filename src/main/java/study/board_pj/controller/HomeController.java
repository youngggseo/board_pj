package study.board_pj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.board_pj.service.UserService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping(value = {"", "/"})
    public String home() {
        return "home";
    }
}

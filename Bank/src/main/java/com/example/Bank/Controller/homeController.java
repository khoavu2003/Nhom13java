package com.example.Bank.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class homeController {
    public String homePage() {
        return "index";
    }
}

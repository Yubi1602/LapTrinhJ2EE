package com.example.bai2_letranbaokha.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String Index(Model model) {
        model.addAttribute("message","hehe");
        return "index";
    }
    @GetMapping("/quan-ly-sach")
    public String book(){

        return "books";
    }


}


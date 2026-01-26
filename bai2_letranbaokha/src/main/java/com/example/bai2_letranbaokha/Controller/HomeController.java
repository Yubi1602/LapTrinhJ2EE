package com.example.bai2_letranbaokha.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("hello")
    public String xinChao() {

        return "Xin chào các bạn";
    }
    @GetMapping("tieuhuy")
    public String tieuHuy() {

        return "Tiêu hủy con gà 55 ký";
    }
}

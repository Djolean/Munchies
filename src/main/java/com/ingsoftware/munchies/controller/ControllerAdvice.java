package com.ingsoftware.munchies.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequiredArgsConstructor
public class ControllerAdvice {

    @RequestMapping("/")
    public String homePage() {
        return "homePage";
    }
}

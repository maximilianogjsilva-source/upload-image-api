package com.api.image.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentController {

    @GetMapping("/")
    public String index(ModelMap model){
        model.put("key","value");
        return "index";
    }
}

package com.osrsge.osrsgetracker.controller;

import com.osrsge.osrsgetracker.service.GeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class GeController {
    private final GeService geService;

    public GeController(GeService geService) {
        this.geService = geService;
    }

    @GetMapping("/")
    public String showPrices(Model model) {
        Map<String, Map<String, Object>> prices = geService.getAllPrices();
        model.addAttribute("prices", prices);
        return "index";
    }

    @PostMapping("/add-item")
    public String addItem(@RequestParam String itemName, @RequestParam int itemId) {
        geService.addItem(itemName, itemId);
        return "redirect:/";  // Suunab tagasi pealehele
    }
}

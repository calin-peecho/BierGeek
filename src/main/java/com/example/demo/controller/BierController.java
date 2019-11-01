package com.example.demo.controller;

import com.example.demo.entity.Bier;
import com.example.demo.repository.BierRepository;
import com.example.demo.service.impl.BierServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BierController {

    private final BierServiceImpl service;
    private final BierRepository repository;

    public BierController(BierServiceImpl service, BierRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping(value = "/index")
    public String getRandom(Model model) {
        Bier bier = service.getRandom();
        model.addAttribute("bier", bier);
        return "index";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute Bier bier, Model model) {
        if(!repository.findById(bier.getId()).isPresent()) {
            repository.save(bier);
        }
        return "saved";
    }

    @PostMapping(value = "/remove_item")
    public String remove(@RequestParam Long id) {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
        return "redirect:/saved";
    }

    @GetMapping(value = "/saved")
    public String showSaved(Model model) {
        model.addAttribute("bierList", repository.findAll());
        return "saved";
    }
}

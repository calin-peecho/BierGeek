package com.example.demo.controller;

import com.example.demo.entity.Bier;
import com.example.demo.repository.BierRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.BierServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BierController {

    private final BierServiceImpl service;
    private final BierRepository bierRepository;
    private final UserRepository userRepository;

    public BierController(BierServiceImpl service, BierRepository repository, UserRepository userRepository) {
        this.service = service;
        this.bierRepository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/random")
    public String getRandom(Model model) {
        Bier randomBier = service.getRandom();
        model.addAttribute("randomBier", randomBier);
        return "/index";
    }

    @PostMapping(value = "/remove_item")
    public String remove(@RequestParam Long id) {
        if(bierRepository.findById(id).isPresent()) {
            bierRepository.deleteById(id);
        }
        return "redirect:/saved";
    }

    @GetMapping(value = "/saved")
    public String showSaved(Model model) {
        model.addAttribute("bierList", bierRepository.findAll());
        return "saved";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

}

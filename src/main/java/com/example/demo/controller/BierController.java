package com.example.demo.controller;

import com.example.demo.entity.Bier;
import com.example.demo.service.BierPunkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BierController {
    //https://api.punkapi.com/v2/beers/random
    //curl https://api.punkapi.com/v2/beers/1
//     "id": 192,
//             "name": "Punk IPA 2007 - 2010",
//             "tagline": "Post Modern Classic. Spiky. Tropical. Hoppy.",
//             "first_brewed": "04/2007",
//             "description":

    @Autowired
    BierPunkService service;

    List<Bier> bierList = new ArrayList<>();

    @RequestMapping(value = "/index")
    public String getRandom(Model model) {
        Bier[] bier = service.getRandom();
        bierList.add(bier[0]);
        model.addAttribute("bier", bierList);
        return "index";
    }
}

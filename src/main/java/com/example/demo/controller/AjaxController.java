package com.example.demo.controller;

import com.example.demo.entity.Bier;
import com.example.demo.repository.BierRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class AjaxController {

    private final BierRepository bierRepository;
    private final UserRepository userRepository;

    public AjaxController(BierRepository repository, UserRepository userRepository) {
        this.bierRepository = repository;
        this.userRepository = userRepository;
    }

    // @JsonView(Views.Public.class) - Optional, filters json data to display.
//    @JsonView(Views.Public.class)
    @RequestMapping(value = "/api/getSearchResult")
    public long getSearchResultViaAjax(@RequestParam long id) {
        //AjaxResponseBody will be converted into json format and send back to the request.
        System.out.println("1");
        return bierRepository.countByUser_Id(id);
    }

    @PostMapping(value = "/save")
    public void save(@ModelAttribute Bier bier, Principal principal) {
        if (!bierRepository.findById(bier.getId()).isPresent()) {
            bier.setUser(userRepository.getByUsername(principal.getName()));
            bierRepository.save(bier);
        }
//        return "/index";
    }
}

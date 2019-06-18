package com.gmail.salahub.nikolay.telegram.bot.application.controller;

import com.gmail.salahub.nikolay.telegram.bot.application.controller.validator.CityValidator;
import com.gmail.salahub.nikolay.telegram.bot.application.service.CityService;
import com.gmail.salahub.nikolay.telegram.bot.application.service.model.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityRestController {

    private final CityService cityService;
    private final CityValidator cityValidator;

    @Autowired
    public CityRestController(CityService cityService,
                              CityValidator cityValidator) {
        this.cityValidator = cityValidator;
        this.cityService = cityService;
    }

    @GetMapping("/api/city")
    public ResponseEntity<List<CityDTO>> showArticles() {
        List<CityDTO> cityDTOS = cityService.getAll();
        return new ResponseEntity<>(cityDTOS, HttpStatus.OK);
    }

    @PostMapping("/api/city")
    private ResponseEntity createItem(@RequestBody CityDTO cityDTO, BindingResult bindingResult) {
        cityValidator.validate(cityDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        cityService.create(cityDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/api/city/{id}")
    public ResponseEntity deleteArticleById(
            @PathVariable(name = "id") Long id) {
        cityService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/api/city/update")
    private ResponseEntity updateItem(@RequestBody CityDTO cityDTO, BindingResult bindingResult) {
        cityValidator.validate(cityDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        cityService.update(cityDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

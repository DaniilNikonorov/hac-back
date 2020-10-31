package app.controller;


import app.dto.classSchoolboy.ClassSchoolboyDto;
import app.service.ClassSchoolboyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/class-schoolboy")
@AllArgsConstructor
public class ClassSchoolboyController {

    private final ClassSchoolboyService service;

    @GetMapping("/")
    public List<ClassSchoolboyDto> getAll() {
        return service.getAll();
    }
}

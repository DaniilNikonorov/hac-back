package app.controller;

import app.dto.schoolboy.SchoolboyDto;
import app.service.SchoolboyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schoolboy")
@AllArgsConstructor
public class SchoolboyController {

    private final SchoolboyService service;

    @GetMapping("/{userId}")
    ResponseEntity<SchoolboyDto> getByUserId(@PathVariable String userId) {
       return service.getById(userId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

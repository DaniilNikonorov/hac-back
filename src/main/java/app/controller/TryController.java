package app.controller;

import app.dto.tries.TryDto;
import app.service.TryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/try")
@AllArgsConstructor
public class TryController {

    private final TryService service;

    @PostMapping("/save")
    TryDto save(@RequestBody TryDto dto) {
        return service.save(dto);
    }

    @GetMapping("/{schoolboyId}")
    List<TryDto> getAll(@PathVariable String schoolboyId, @RequestParam String taskId) {
        return service.getAllBySchoolboyTries(schoolboyId, taskId);
    }
}

package app.controller;

import app.dto.task.TaskTeacherDto;
import app.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping("/save")
    TaskTeacherDto save(@RequestBody TaskTeacherDto dto) {
        return service.save(dto);
    }

    @GetMapping("/")
    List<TaskTeacherDto> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}

package app.controller;

import app.dto.task.TaskTeacherDto;
import app.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping("/save")
    TaskTeacherDto save(@RequestBody TaskTeacherDto dto) {
        return service.save(dto);
    }

}

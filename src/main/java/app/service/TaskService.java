package app.service;

import app.dto.task.TaskTeacherDto;
import app.dto.testTask.TestTaskDto;
import app.entity.ClassSchoolboy;
import app.entity.Task;
import app.entity.Teacher;
import app.entity.TestTask;
import app.repository.TaskRepository;
import app.repository.TestTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final TestTaskRepository testTaskRepository;

    public TaskTeacherDto save(TaskTeacherDto dto) {
        Task entity = toEntity(dto);
        List<TestTask> testTaskList = entity.getTestTaskList();
        entity.setTestTaskList(new ArrayList<>());
        repository.save(entity);
        testTaskList.forEach(testTaskRepository::save);
        entity.setTestTaskList(testTaskList);

        return toDto(entity);
    }

    private Task toEntity(TaskTeacherDto dto) {
        Task entity = new Task();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLegend(dto.getLegend());

        Teacher teacher = new Teacher();
        teacher.setId(dto.getTeacherId());

        entity.setTeacher(teacher);

        List<TestTask> testTaskList = new ArrayList<>();
        dto.getTestTaskList().forEach(test -> {
            TestTask testTask = new TestTask();
            testTask.setId(test.getId());
            testTask.setTaskId(entity.getId());
            testTask.setInput(test.getInput());
            testTask.setOutput(test.getOutput());
            testTaskList.add(testTask);
        });

        entity.setTestTaskList(testTaskList);


        //Манипуляции для того, чтобы сохранялась инфа в кросс таблицу Класс-Задание
        List<ClassSchoolboy> classList = new ArrayList<>();
        dto.getClassSchoolboyList().forEach(cl -> {
            ClassSchoolboy classSchoolboy = new ClassSchoolboy();
            classSchoolboy.setId(cl.getId());
            classList.add(classSchoolboy);
        });

        entity.setClassSchoolboyList(classList);

        return entity;
    }

    public static TaskTeacherDto toDto(Task entity) {
        TaskTeacherDto dto = new TaskTeacherDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setTeacherId(entity.getTeacher().getId());
        dto.setLegend(entity.getLegend());

        dto.setTestTaskList(
                entity.getTestTaskList()
                        .stream()
                        .map(TestTaskDto::toDto)
                        .collect(Collectors.toList())
        );

        dto.setClassSchoolboyList(
                entity.getClassSchoolboyList()
                        .stream()
                        .map(ClassSchoolboyService::toDto)
                        .collect(Collectors.toList())
        );

        return dto;
    }
}

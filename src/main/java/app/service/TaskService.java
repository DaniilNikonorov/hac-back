package app.service;

import app.dto.task.TaskTeacherDto;
import app.dto.testTask.TestCasesDto;
import app.entity.ClassSchoolboy;
import app.entity.Task;
import app.entity.Teacher;
import app.entity.TestCase;
import app.repository.TaskRepository;
import app.repository.TestCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final TestCaseRepository testCaseRepository;

    public List<TaskTeacherDto> getAll() {
        return repository.findAll()
                .stream()
                .map(TaskService::toDto)
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public TaskTeacherDto save(TaskTeacherDto dto) {
        Task entity = toEntity(dto);
        List<TestCase> testCaseList = entity.getTestCaseList();
        entity.setTestCaseList(new ArrayList<>());
        repository.save(entity);
        testCaseList.forEach(testCaseRepository::save);
        entity.setTestCaseList(testCaseList);

        return toDto(entity);
    }

    public String getTestCaseInput(String taskId) {
        Optional<TestCase> testTask = testCaseRepository.findById(taskId);
        return testTask.map(TestCase::getInput).orElse(null);
    }

    public List<TestCase> getTestCasesList(String taskId) {
        return testCaseRepository.findAllByTaskId(taskId);
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

        List<TestCase> testCaseList = new ArrayList<>();
        dto.getTestTaskList().forEach(test -> {
            TestCase testCase = new TestCase();
            testCase.setId(test.getId());
            testCase.setTaskId(entity.getId());
            testCase.setInput(test.getInput());
            testCase.setOutput(test.getOutput());
            testCaseList.add(testCase);
        });

        entity.setTestCaseList(testCaseList);


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
                entity.getTestCaseList()
                        .stream()
                        .map(TestCasesDto::toDto)
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

package app.service;

import app.dto.schoolboy.SchoolboyDto;
import app.dto.task.TaskSchoolboyDto;
import app.dto.tries.TryDto;
import app.entity.Schoolboy;
import app.entity.Task;
import app.repository.SchoolboyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SchoolboyService {

    private final SchoolboyRepository schoolboyRepository;
    private final TryService tryService;

    public Optional<SchoolboyDto> getById(String id) {
        return schoolboyRepository.findByUserId(id).map(this::toDto);
    }

    public SchoolboyDto toDto(Schoolboy entity) {
        SchoolboyDto dto = new SchoolboyDto();
        dto.setId(entity.getId());

        List<Task> tasks = entity.getClassSchoolboy().getTaskList();
        if (tasks.size() > 0) {
            List<TaskSchoolboyDto> taskListDto = tasks
                    .stream()
                    .map(task -> taskToDto(task, entity.getId()))
                    .collect(Collectors.toList());
            dto.setTaskList(taskListDto);
        }
        return dto;
    }

    public TaskSchoolboyDto taskToDto(Task entity, String schoolboyId) {
       TaskSchoolboyDto dto = new TaskSchoolboyDto();
       dto.setId(entity.getId());
       dto.setName(entity.getName());
       dto.setDescription(entity.getDescription());
       dto.setLegend(entity.getLegend());
       Integer bestResult = 0;

       List<TryDto> triesList =  tryService.getAllBySchoolboyTries(schoolboyId, entity.getId());
       if (triesList.size() > 0) {
           bestResult = triesList
                   .stream()
                   .map(TryDto::getResult)
                   .reduce(Integer.MIN_VALUE, (left, right) -> left > right ? left : right);
       }

       dto.setBestResult(bestResult);

       return dto;
    }

}

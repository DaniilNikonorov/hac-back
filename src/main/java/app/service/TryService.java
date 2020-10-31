package app.service;

import app.dto.tries.TryDto;
import app.entity.Schoolboy;
import app.entity.Task;
import app.entity.Try;
import app.repository.TryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TryService {

    public final TryRepository repository;

    public List<TryDto> getAllBySchoolboyTries(String schoolboyId, String taskId) {
        List<Try> listEntity = repository.findAllBySchoolboyIdAndTaskId(schoolboyId, taskId);
        if (listEntity.size() > 0) {
            return listEntity
                    .stream().map(this::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public TryDto save(TryDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    private TryDto toDto(Try entity) {
        TryDto dto = new TryDto();
        dto.setId(entity.getId());
        dto.setSchoolboyId(entity.getSchoolboy().getId());
        dto.setTaskId(entity.getTask().getId());
        dto.setTryNumber(entity.getTryNumber());
        dto.setCode(entity.getCode());

        if (entity.getInput() != null) {
            dto.setInput(entity.getInput());
        }

        if (entity.getOutput() != null) {
            dto.setOutput(entity.getOutput());
        }
        dto.setResult(entity.getResult());

        return dto;
    }

    private Try toEntity(TryDto dto) {
        Try entity = new Try();
        entity.setId(dto.getId());
        entity.setTryNumber(dto.getTryNumber());
        entity.setCode(dto.getCode());
        entity.setInput(dto.getInput());
        entity.setOutput(dto.getOutput());
        entity.setResult(dto.getResult());

        Schoolboy schoolboy = new Schoolboy();
        schoolboy.setId(dto.getSchoolboyId());

        Task task = new Task();
        task.setId(dto.getTaskId());

        entity.setSchoolboy(schoolboy);
        entity.setTask(task);

        return entity;
    }

}

package app.service;

import app.dto.classSchoolboy.ClassSchoolboyDto;
import app.entity.ClassSchoolboy;
import app.repository.ClassSchoolboyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClassSchoolboyService {

    private final ClassSchoolboyRepository repository;

    public List<ClassSchoolboyDto> getAll() {
        List<ClassSchoolboy> classList = repository.findAll();
        return classList.size() > 0
                ? classList.stream().map(ClassSchoolboyService::toDto).collect(Collectors.toList())
                : null;
    }

    static ClassSchoolboyDto toDto(ClassSchoolboy entity) {
        ClassSchoolboyDto dto = new ClassSchoolboyDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }
}

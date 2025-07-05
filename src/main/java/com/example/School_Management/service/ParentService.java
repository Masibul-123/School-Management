package com.example.School_Management.service;



import com.example.School_Management.dto.ParentDto;
import com.example.School_Management.entity.Parent;
import com.example.School_Management.repository.ParentRepository;
import com.example.School_Management.transformer.ParentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ParentTransformer parentTransformer;

    public List<ParentDto> findAll() {
        return parentRepository.findAll()
                .stream()
                .map(parentTransformer::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<ParentDto> findById(long id) {
        return parentRepository.findById(id)
                .map(parentTransformer::convertEntityToDto);
    }

    public ParentDto save(ParentDto parentDto) {

        Parent parent = parentTransformer.convertDtoToEntity(parentDto);
        Parent savedParent = parentRepository.save(parent);
        return parentTransformer.convertEntityToDto(savedParent);
    }

    public ParentDto update(long id, ParentDto parentDto) {
        Optional<Parent> existingParent = parentRepository.findById(id);
        if (existingParent.isEmpty()) {
            return null;
        }

        Parent updatedParent = parentTransformer.convertDtoToEntity(parentDto);
        updatedParent.setId(id);
        Parent savedParent = parentRepository.save(updatedParent);

        return parentTransformer.convertEntityToDto(savedParent);
    }

    public String deleteById(long id) {
        if (!parentRepository.existsById(id)) {
            return null;
        }
        parentRepository.deleteById(id);
        return "Parent with ID " + id + " deleted successfully.";
    }
}

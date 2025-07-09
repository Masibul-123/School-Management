package com.gmmps.service;

import com.gmmps.dto.ParentDto;
import com.gmmps.entity.Parent;
import com.gmmps.repository.ParentRepository;
import com.gmmps.transformer.ParentTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParentService {

    private final ParentRepository parentRepository;
    private final  ParentTransformer parentTransformer;

    public ParentService(ParentRepository parentRepository, ParentTransformer parentTransformer) {
        this.parentRepository = parentRepository;
        this.parentTransformer = parentTransformer;
    }

    public List<ParentDto> getAllParents() {
        return parentRepository.findAll()
                .stream()
                .map(parentTransformer::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<ParentDto> getParentById(long id) {
        return parentRepository.findById(id)
                .map(parentTransformer::convertEntityToDto);
    }

    @Transactional
    public String deleteParentDetails(long id) {
        if (!parentRepository.existsById(id))
            return null;
        parentRepository.deleteById(id);
        return "Parent with ID " + id + " deleted successfully.";
    }
}

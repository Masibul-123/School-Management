package com.example.School_Management.service;

import com.example.School_Management.dto.SectionDto;
import com.example.School_Management.entity.Section;
import com.example.School_Management.repository.SectionRepository;
import com.example.School_Management.transformer.SectionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SectionTransformer sectionTransformer;

    public List<SectionDto> findAll() {
        return sectionRepository.findAll()
                .stream()
                .map(sectionTransformer::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<SectionDto> findById(long id) {
        return sectionRepository.findById(id)
                .map(sectionTransformer::convertEntityToDto);
    }

    public SectionDto save(SectionDto sectionDto) {
        Section section = sectionTransformer.convertDtoToEntity(sectionDto);
        Section saved = sectionRepository.save(section);
        return sectionTransformer.convertEntityToDto(saved);
    }

    @Transactional
    public SectionDto update(long id, SectionDto sectionDto) {
        Optional<Section> existing = sectionRepository.findById(id);
        if (existing.isEmpty()) {
            return null;
        }

        Section section = sectionTransformer.convertDtoToEntity(sectionDto);
        Section updated = sectionRepository.save(section);
        return sectionTransformer.convertEntityToDto(updated);
    }

    @Transactional
    public String deleteById(long id) {
        if (!sectionRepository.existsById(id)) {
            return null;
        }

        sectionRepository.deleteById(id);
        return "Section with ID " + id + " deleted successfully.";
    }
}

package com.gmmps.service;

import com.gmmps.dto.SectionInfoDto;
import com.gmmps.entity.SectionInfo;
import com.gmmps.repository.SectionInfoRepository;
import com.gmmps.transformer.SectionTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionService {

    private final SectionInfoRepository sectionInfoRepository;
    private final  SectionTransformer sectionTransformer;

    public SectionService(SectionInfoRepository sectionInfoRepository, SectionTransformer sectionTransformer) {
        this.sectionInfoRepository = sectionInfoRepository;
        this.sectionTransformer = sectionTransformer;
    }

    public List<SectionInfoDto> getAllSections() {
        return sectionInfoRepository.findAll()
                .stream()
                .map(sectionTransformer::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<SectionInfoDto> getSectionById(long id) {
        return sectionInfoRepository.findById(id)
                .map(sectionTransformer::convertEntityToDto);
    }

    public SectionInfoDto addSection(SectionInfoDto sectionInfoDto) {
        SectionInfo updatedSectionInfo = sectionTransformer.convertDtoToEntity(sectionInfoDto);
        SectionInfo returnedSectionInfo = sectionInfoRepository.save(updatedSectionInfo);
        return sectionTransformer.convertEntityToDto(returnedSectionInfo);
    }

    @Transactional
    public SectionInfoDto updateSectionInfo(long sectionId, SectionInfoDto sectionInfoDto) {
        Optional<SectionInfo> existing = sectionInfoRepository.findById(sectionId);
        if (existing.isEmpty())
            return null;
        SectionInfo updatedSectionInfo = sectionTransformer.convertDtoToEntity(sectionInfoDto);
        updatedSectionInfo.setId(sectionId);
        SectionInfo returnedSectionInfo = sectionInfoRepository.save(updatedSectionInfo);
        return sectionTransformer.convertEntityToDto(returnedSectionInfo);
    }

    @Transactional
    public String deleteSection(long sectionId) {
        if (!sectionInfoRepository.existsById(sectionId))
            return null;
        sectionInfoRepository.deleteById(sectionId);
        return "Section with ID " + sectionId + " deleted successfully.";
    }
}

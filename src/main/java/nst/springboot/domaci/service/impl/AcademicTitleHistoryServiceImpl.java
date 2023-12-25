package nst.springboot.domaci.service.impl;

import nst.springboot.domaci.converter.impl.AcademicTitleHistoryConverter;
import nst.springboot.domaci.dto.AcademicTitleHistoryDto;
import nst.springboot.domaci.model.AcademicTitleHistory;
import nst.springboot.domaci.model.AcademicTitleHistoryId;
import nst.springboot.domaci.repository.AcademicTitleHistoryRepository;
import nst.springboot.domaci.service.AcademicTitleHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AcademicTitleHistoryServiceImpl implements AcademicTitleHistoryService {
    private AcademicTitleHistoryRepository academicTitleHistoryRepository;
    private AcademicTitleHistoryConverter academicTitleHistoryConverter;

    public AcademicTitleHistoryServiceImpl(AcademicTitleHistoryRepository academicTitleHistoryRepository, AcademicTitleHistoryConverter academicTitleHistoryConverter) {
        this.academicTitleHistoryRepository = academicTitleHistoryRepository;
        this.academicTitleHistoryConverter = academicTitleHistoryConverter;
    }

    @Override
    public AcademicTitleHistoryDto save(AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception {
        AcademicTitleHistory academicTitleHistory = academicTitleHistoryRepository.save(academicTitleHistoryConverter.toEntity(academicTitleHistoryDto));
        return academicTitleHistoryConverter.toDto(academicTitleHistory);
    }

    @Override
    public List<AcademicTitleHistoryDto> getAll(Pageable pageable) {
        return academicTitleHistoryRepository.findAll(pageable).stream().map(e -> academicTitleHistoryConverter.toDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<AcademicTitleHistoryDto> getAll() {
        return academicTitleHistoryRepository.findAll().stream().map(e -> academicTitleHistoryConverter.toDto(e)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long memberId, Long academicTitleId) throws Exception {
        Optional<AcademicTitleHistory> academicTitleHistory = academicTitleHistoryRepository.findById(new AcademicTitleHistoryId(memberId, academicTitleId));
        if(academicTitleHistory.isEmpty()) throw new Exception("Academic title history does not exist!");
        academicTitleHistoryRepository.delete(academicTitleHistory.get());

    }

    @Override
    public void update(AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception {

    }

    @Override
    public AcademicTitleHistoryDto findById(Long memberId, Long academicTitleId) throws Exception {
        Optional<AcademicTitleHistory> academicTitleHistory = academicTitleHistoryRepository.findById(new AcademicTitleHistoryId(memberId, academicTitleId));
        if(academicTitleHistory.isEmpty()) throw new Exception("Academic title history does not exist!");
        return academicTitleHistoryConverter.toDto(academicTitleHistory.get());
    }
}

package br.edu.ifpi.infovita.service.exam;

import br.edu.ifpi.infovita.domain.Equipment;
import br.edu.ifpi.infovita.domain.Exam;
import br.edu.ifpi.infovita.repository.ExamRepository;
import br.edu.ifpi.infovita.service.equipment.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final EquipmentService equipmentService;
    private final ExamRepository examRepository;

    @Override
    public Exam findById(Long id){
        return examRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not found!"));
    }

    @Override
    public Page<Exam> findAll(Pageable pageable){
        return examRepository.findAll(pageable);
    }

    @Override
    public Exam save(Exam exam){
        return examRepository.save(exam);
    }

    @Override
    public void deleteById(Long id){
        Exam Exam = findById(id);
        examRepository.delete(Exam);
    }

    @Override
    public void update(Exam exam){
        Exam examFound = findById(exam.getId());
        examFound.setName(exam.getName());

        examRepository.save(examFound);
    }

    @Override
    public void addEquipmentToExam(Long examId, Long equipmentId){
        Exam exam = findById(examId);
        Equipment equipment = equipmentService.findById(equipmentId);

        exam.getEquipment().add(equipment);

        examRepository.save(exam);
    }

    @Override
    public void removeEquipmentFromExam(Long examId, Long equipmentId){
        Exam exam = findById(examId);
        Equipment equipment = equipmentService.findById(equipmentId);

        examRepository.deleteByExamAndEquipment(exam.getId(), equipment.getId());
    }
}

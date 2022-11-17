package br.edu.ifpi.infovita.service;

import br.edu.ifpi.infovita.domain.Equipment;
import br.edu.ifpi.infovita.domain.Exam;
import br.edu.ifpi.infovita.repository.ExamRepository;
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
public class ExamService {

    private final EquipmentService equipmentService;
    private final ExamRepository examRepository;

    public Exam findById(Long id){
        return examRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not found!"));
    }

    public Page<Exam> findAll(Pageable pageable){
        return examRepository.findAll(pageable);
    }

    public Exam save(Exam exam){
        return examRepository.save(exam);
    }

    public void deleteById(Long id){
        Exam Exam = findById(id);
        examRepository.delete(Exam);
    }

    public void update(Exam exam){
        Exam examFound = findById(exam.getId());
        examFound.setName(exam.getName());

        examRepository.save(examFound);
    }

    public void addEquipmentToExam(Long examId, Long equipmentId){
        Exam exam = findById(examId);
        Equipment equipment = equipmentService.findById(equipmentId);

        exam.getEquipment().add(equipment);

        examRepository.save(exam);
    }

    public void removeEquipmentFromExam(Long examId, Long equipmentId){
        Exam exam = findById(examId);
        Equipment equipment = equipmentService.findById(equipmentId);

        examRepository.deleteByExamAndEquipment(exam.getId(), equipment.getId());
    }
}

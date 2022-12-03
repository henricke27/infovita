package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.domain.Exam;
import br.edu.ifpi.infovita.dto.exame.ExamPostRequestBody;
import br.edu.ifpi.infovita.dto.exame.ExamPutRequestBody;
import br.edu.ifpi.infovita.dto.exame.ExamResponseBody;
import br.edu.ifpi.infovita.dto.exameEquipamento.ExamEquipmentDeleteRequestBody;
import br.edu.ifpi.infovita.dto.exameEquipamento.ExamEquipmentPostRequestBody;
import br.edu.ifpi.infovita.service.exam.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping(path = "/by-name/{name}")
    public ResponseEntity<List<ExamResponseBody>> findAllByName(@PathVariable String name){
        List<Exam> exams = examService.findAllByName(name);
        List<ExamResponseBody> examResponseBodies = exams.stream()
                .map(ExamResponseBody::convertExamToResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(examResponseBodies, HttpStatus.OK);
    }

    @PostMapping(path = "/add-equipment")
    public ResponseEntity<Void> addEquipamentoToExame(@RequestBody ExamEquipmentPostRequestBody eeprb){
        examService.addEquipmentToExam(eeprb.getExamId(), eeprb.getEquipmentId());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/remove-equipment")
    public ResponseEntity<Void> removeEquipamentoFromExame(@RequestBody ExamEquipmentDeleteRequestBody eedrb){
        examService.removeEquipmentFromExam(eedrb.getExamId(), eedrb.getEquipmentId());

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExamResponseBody> findById(@PathVariable Long id){
        Exam exam = examService.findById(id);
        ExamResponseBody examResponseBody = ExamResponseBody.convertExamToResponseDto(exam);

        return new ResponseEntity<>(examResponseBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ExamResponseBody>> findAll(Pageable pageable){
        List<Exam> exams = examService.findAll(pageable).getContent();
        List<ExamResponseBody> examResponseBodies = exams.stream()
                .map(ExamResponseBody::convertExamToResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(examResponseBodies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExamResponseBody> save(@RequestBody ExamPostRequestBody exam){
        Exam examToBeSaved = Exam.builder()
                .name(exam.getName())
                .build();

        Exam examSaved = examService.save(examToBeSaved);
        ExamResponseBody examResponseBody = ExamResponseBody.convertExamToResponseDto(examSaved);

        return new ResponseEntity<>(examResponseBody, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ExamPutRequestBody exam){
        Exam examToBeUpdated = Exam.builder()
                .id(exam.getId())
                .name(exam.getName())
                .build();

        examService.update(examToBeUpdated);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        examService.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

}

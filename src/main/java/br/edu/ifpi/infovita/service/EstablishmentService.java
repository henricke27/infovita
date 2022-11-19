package br.edu.ifpi.infovita.service;

import br.edu.ifpi.infovita.domain.Establishment;
import br.edu.ifpi.infovita.exception.BadRequestException;
import br.edu.ifpi.infovita.exception.EstablishmentNotFoundException;
import br.edu.ifpi.infovita.repository.EstablishmentEquipmentRepository;
import br.edu.ifpi.infovita.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EstablishmentService {
    private final EstablishmentRepository establishmentRepository;
    private final EstablishmentEquipmentRepository establishmentEquipmentRepository;

    public List<Establishment> findAllByExam(Long examId){
        return establishmentRepository.findAllByExam(examId);
    }

    public Establishment findById(Long id){
        return establishmentRepository.findById(id)
                .orElseThrow(() -> new EstablishmentNotFoundException("Establishment not found!"));
    }

    public List<Establishment> findAll(){
        return establishmentRepository.findAll();
    }

    public Page<Establishment> findAllPageable(Pageable pageable){
        return establishmentRepository.findAll(pageable);
    }

    public Establishment saveWithAddressCompose(Establishment establishment){
        return establishmentRepository.save(establishment);
    }

    public void deleteById(Long id){
        Establishment establishment = findById(id);
        establishmentEquipmentRepository.deleteByEstablishment(establishment);
        establishmentRepository.delete(establishment);
    }

    public void updateWithAddressCompose(Establishment establishment){
        Establishment establishmentFound = findById(establishment.getId());
        establishment.getAddress().setId(establishmentFound.getAddress().getId());

        establishmentRepository.save(establishment);
    }

}

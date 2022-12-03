package br.edu.ifpi.infovita.service.establishment;

import br.edu.ifpi.infovita.domain.Establishment;
import br.edu.ifpi.infovita.exception.EstablishmentNotFoundException;
import br.edu.ifpi.infovita.repository.EstablishmentEquipmentRepository;
import br.edu.ifpi.infovita.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EstablishmentServiceImpl implements EstablishmentService{
    private final EstablishmentRepository establishmentRepository;
    private final EstablishmentEquipmentRepository establishmentEquipmentRepository;

    @Override
    public List<Establishment> findAllByExam(Long examId){
        return establishmentRepository.findAllByExam(examId);
    }

    @Override
    public Establishment findById(Long id){
        return establishmentRepository.findById(id)
                .orElseThrow(() -> new EstablishmentNotFoundException("Establishment not found!"));
    }

    @Override
    public List<Establishment> findAll(){
        return establishmentRepository.findAll();
    }

    @Override
    public Page<Establishment> findAllPageable(Pageable pageable){
        return establishmentRepository.findAll(pageable);
    }

    @Override
    public Establishment saveWithAddressCompose(Establishment establishment){
        return establishmentRepository.save(establishment);
    }

    @Override
    public void deleteById(Long id){
        Establishment establishment = findById(id);
        establishmentEquipmentRepository.deleteByEstablishment(establishment);
        establishmentRepository.delete(establishment);
    }

    @Override
    public void updateWithAddressCompose(Establishment establishment){
        Establishment establishmentFound = findById(establishment.getId());
        establishment.getAddress().setId(establishmentFound.getAddress().getId());

        establishmentRepository.save(establishment);
    }

    @Override
    public List<Establishment> findAllByName(String name) {
        return establishmentRepository.findAllByNameContainingIgnoreCase(name);
    }
}

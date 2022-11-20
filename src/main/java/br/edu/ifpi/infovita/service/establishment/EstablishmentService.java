package br.edu.ifpi.infovita.service.establishment;

import br.edu.ifpi.infovita.domain.Establishment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EstablishmentService {
    List<Establishment> findAll();
    List<Establishment> findAllByExam(Long examId);
    Establishment findById(Long id);
    Page<Establishment> findAllPageable(Pageable pageable);
    Establishment saveWithAddressCompose(Establishment establishment);
    void deleteById(Long id);
    void updateWithAddressCompose(Establishment establishment);
}

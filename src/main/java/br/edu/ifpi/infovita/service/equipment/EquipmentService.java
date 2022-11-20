package br.edu.ifpi.infovita.service.equipment;

import br.edu.ifpi.infovita.domain.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipmentService {
    Equipment findById(Long id);
    Page<Equipment> findAll(Pageable pageable);
    Equipment save(Equipment equipment);
    void deleteById(Long id);
    void update(Equipment equipment);
}

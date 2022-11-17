package br.edu.ifpi.infovita.service;

import br.edu.ifpi.infovita.domain.Equipment;
import br.edu.ifpi.infovita.exception.BadRequestException;
import br.edu.ifpi.infovita.repository.EquipmentRepository;
import br.edu.ifpi.infovita.repository.EstablishmentEquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EquipmentService {

    private final EstablishmentEquipmentRepository establishmentEquipmentRepository;
    private final EquipmentRepository equipmentRepository;

    public Equipment findById(Long id){
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Equipment not found!"));
    }

    public Page<Equipment> findAll(Pageable pageable){
        return equipmentRepository.findAll(pageable);
    }

    public Equipment save(Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    public void deleteById(Long id){
        Equipment equipment = findById(id);
        establishmentEquipmentRepository.deleteByEquipment(equipment);
        equipmentRepository.delete(equipment);
    }

    public void update(Equipment equipment){
        findById(equipment.getId());
        equipmentRepository.save(equipment);
    }

}

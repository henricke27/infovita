package br.edu.ifpi.infovita.service.equipment;

import br.edu.ifpi.infovita.domain.Equipment;
import br.edu.ifpi.infovita.exception.EquipmentNotFoundException;
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
public class EquipmentServiceImpl implements EquipmentService {

    private final EstablishmentEquipmentRepository establishmentEquipmentRepository;
    private final EquipmentRepository equipmentRepository;

    @Override
    public Equipment findById(Long id){
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found!"));
    }

    @Override
    public Page<Equipment> findAll(Pageable pageable){
        return equipmentRepository.findAll(pageable);
    }

    @Override
    public Equipment save(Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    @Override
    public void deleteById(Long id){
        Equipment equipment = findById(id);
        establishmentEquipmentRepository.deleteByEquipment(equipment);
        equipmentRepository.delete(equipment);
    }

    @Override
    public void update(Equipment equipment){
        findById(equipment.getId());
        equipmentRepository.save(equipment);
    }

}

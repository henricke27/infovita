package br.edu.ifpi.infovita.service.establishmentEquipment;

import br.edu.ifpi.infovita.domain.Equipment;
import br.edu.ifpi.infovita.domain.Establishment;
import br.edu.ifpi.infovita.domain.EstablishmentEquipment;
import br.edu.ifpi.infovita.exception.BadRequestException;
import br.edu.ifpi.infovita.repository.EstablishmentEquipmentRepository;
import br.edu.ifpi.infovita.service.equipment.EquipmentService;
import br.edu.ifpi.infovita.service.establishment.EstablishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EstablishmentEquipmentServiceImpl implements EstablishmentEquipmentService {
    private final EstablishmentEquipmentRepository establishmentEquipmentRepository;
    private final EstablishmentService establishmentService;
    private final EquipmentService equipmentService;

    @Override
    public void addEquipmentToEstablishment(EstablishmentEquipment establishmentEquipment){
        Establishment establishment = establishmentService.findById(establishmentEquipment
                .getEstablishment()
                .getId());

        Equipment equipment = equipmentService.findById(establishmentEquipment
                .getEquipment()
                .getId());

        findByEstablishmentAndEquipment(establishment.getId(), equipment.getId())
                .ifPresent((ee) -> {throw new BadRequestException("Establishment has this equipment");});

        establishmentEquipment.setEstablishment(establishment);
        establishmentEquipment.setEquipment(equipment);

        establishmentEquipmentRepository.save(establishmentEquipment);
    }

    @Override
    public void deleteByEstablishmentAndEquipment(Establishment establishment, Equipment equipment) {
        Establishment establishmentFound = establishmentService.findById(establishment.getId());
        Equipment equipmentFound = equipmentService.findById(equipment.getId());

        findByEstablishmentAndEquipment(establishment.getId(), equipment.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        establishmentEquipmentRepository.deleteByEstablishmentAndEquipment(establishmentFound.getId(), equipmentFound.getId());
    }

    private Optional<EstablishmentEquipment> findByEstablishmentAndEquipment(Long establishmentId, Long equipmentId){
        return establishmentEquipmentRepository.findByEstablishmentAndEquipment(establishmentId, equipmentId);
    }
}

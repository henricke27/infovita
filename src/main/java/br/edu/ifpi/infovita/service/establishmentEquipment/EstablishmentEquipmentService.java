package br.edu.ifpi.infovita.service.establishmentEquipment;

import br.edu.ifpi.infovita.domain.Equipment;
import br.edu.ifpi.infovita.domain.Establishment;
import br.edu.ifpi.infovita.domain.EstablishmentEquipment;

public interface EstablishmentEquipmentService {
    void addEquipmentToEstablishment(EstablishmentEquipment establishmentEquipment);
    void deleteByEstablishmentAndEquipment(Establishment establishment, Equipment equipment);
}

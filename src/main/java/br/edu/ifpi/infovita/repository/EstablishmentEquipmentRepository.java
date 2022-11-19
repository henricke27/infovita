package br.edu.ifpi.infovita.repository;

import br.edu.ifpi.infovita.domain.Equipment;
import br.edu.ifpi.infovita.domain.Establishment;
import br.edu.ifpi.infovita.domain.EstablishmentEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstablishmentEquipmentRepository extends JpaRepository<EstablishmentEquipment, Long> {
    void deleteByEquipment(Equipment equipment);

    void deleteByEstablishment(Establishment establishment);

    @Modifying
    @Query(value = "delete from establishment_equipment where establishment_id = ?1 and equipment_id = ?2", nativeQuery = true)
    void deleteByEstablishmentAndEquipment(Long establishmentId, Long equipmentId);

    @Query(value = "select * from establishment_equipment where establishment_id = ?1 and equipment_id = ?2", nativeQuery = true)
    Optional<EstablishmentEquipment> findByEstablishmentAndEquipment(Long establishmentId, Long equipmentId);
}

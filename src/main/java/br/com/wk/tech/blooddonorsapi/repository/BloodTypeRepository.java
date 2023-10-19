package br.com.wk.tech.blooddonorsapi.repository;

import br.com.wk.tech.blooddonorsapi.model.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodTypeRepository extends JpaRepository<BloodType, Long> {

    @Query(value = "SELECT ts.* FROM tipo_sanguineo ts " +
            "WHERE ts.type = :type",
            nativeQuery = true)
    BloodType findByBloodType(@Param("type") String type);
}

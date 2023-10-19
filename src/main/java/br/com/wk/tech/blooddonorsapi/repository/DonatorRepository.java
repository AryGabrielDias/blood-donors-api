package br.com.wk.tech.blooddonorsapi.repository;

import br.com.wk.tech.blooddonorsapi.model.Donator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DonatorRepository extends JpaRepository<Donator, Long> {

    @Query(value = "SELECT d.* FROM doador d " +
            "WHERE d.id = :donatorId",
            nativeQuery = true)
    Donator findByDonatorId(@Param("donatorId") Long donatorId);
}

package br.com.wk.tech.blooddonorsapi.repository;

import br.com.wk.tech.blooddonorsapi.model.BloodType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodTypeRepository extends CrudRepository<BloodType, Long> {
}

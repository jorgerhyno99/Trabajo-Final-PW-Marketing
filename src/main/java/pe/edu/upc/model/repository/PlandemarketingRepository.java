package pe.edu.upc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.entity.PlanDeMarketing;

@Repository
public interface PlandemarketingRepository extends JpaRepository<PlanDeMarketing,Integer> {
		List<PlanDeMarketing> findByName(String name) throws Exception;
	
}

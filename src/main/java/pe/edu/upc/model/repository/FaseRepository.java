package pe.edu.upc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.entity.Fase;

@Repository
public interface FaseRepository extends JpaRepository<Fase,Integer> {

	
}
